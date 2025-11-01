package me.smorodin.exam.service;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.smorodin.exam.entity.Decider;
import me.smorodin.exam.repository.DeciderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DeciderService {
    DeciderRepository deciderRepository;
    EntityManager entityManager;


    public Optional<Decider> findById(Long id) {
        return deciderRepository.findById(id);
    }

    @Transactional
    public Decider findOrCreateDecider(Long id) {
        return findById(id).orElseGet(() -> addDeciderUnsafe(new Decider(id)));
    }

    public Iterable<Decider> allDeciders() {
        return deciderRepository.findAll();
    }

    // Optimize insert (without extra select)
    private Decider addDeciderUnsafe(Decider decider) {
        entityManager.persist(decider);
        return decider;
    }

    public void removeDecider(Decider decider) {
        deciderRepository.delete(decider);
    }
}
