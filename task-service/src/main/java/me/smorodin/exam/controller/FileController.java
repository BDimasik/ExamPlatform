package me.smorodin.exam.controller;

import lombok.RequiredArgsConstructor;
import me.smorodin.exam.dto.TaskDTO;
import me.smorodin.exam.entity.Task;
import me.smorodin.exam.repository.TaskRepository;
import me.smorodin.exam.service.MinioService;
import me.smorodin.exam.util.FileExtensionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/task/file")
@RequiredArgsConstructor
public class FileController {
    private final MinioService minioService;
    private final TaskRepository taskRepository;
    private final FileExtensionUtils fileExtensionUtils;

    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<TaskDTO> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "taskId") Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("No such task"));
        try {
            String generatedName = UUID.randomUUID().toString();

            String savedName = minioService.uploadFile(file, generatedName + "." + fileExtensionUtils.getExtension(
                    file.getOriginalFilename()
            ));

            task.setS3FileId(savedName);

            return ResponseEntity.ok(new TaskDTO(task));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("name") String objectName) {
        try {
            byte[] fileData = minioService.downloadFile(objectName);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + objectName + "\"")
                    .body(fileData);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
