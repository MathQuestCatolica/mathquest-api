package com.mathquest.mathquest.feature.challenges.service;

import com.mathquest.mathquest.feature.challenges.domain.Challenges;
import com.mathquest.mathquest.feature.challenges.dto.ChallengesDTO;
import com.mathquest.mathquest.feature.challenges.repository.ChallengesRepository;
import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChallengesService {

    private final ChallengesRepository challengesRepository;

    public ChallengesService(ChallengesRepository challengesRepository) {
        this.challengesRepository = challengesRepository;
    }

    public Challenges createChallenge(ChallengesDTO challengesDTO) {
        Challenges challenges = Challenges.builder()
                .title(challengesDTO.getTitle())
                .description(challengesDTO.getDescription())
                .time(challengesDTO.getTime())
                .build();

        return challengesRepository.save(challenges);
    }

    public List<Challenges> getAllChallenges() {
        return challengesRepository.findAll();
    }

    public ChallengesDTO getChallegeById(Long id) {
        Challenges challenge = challengesRepository.findById(id).orElse(null);

        if (Objects.isNull(challenge)) return null;

        return ChallengesDTO.builder()
                .title(challenge.getTitle())
                .description(challenge.getDescription())
                .time(challenge.getTime())
                .build();
    }

    public void deleteChallenge(Long id) {
        if (!challengesRepository.existsById(id)) {
            throw new RuntimeException("Player not found with id: " + id);
        }
        challengesRepository.deleteById(id);
    }

    public Challenges editChallenge(Long id, ChallengesDTO challengesDTO) {
        Challenges challenges = challengesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        challenges.setDescription(challengesDTO.getDescription());
        challenges.setTitle(challengesDTO.getTitle());
        challenges.setTime(challengesDTO.getTime());

        return challengesRepository.save(challenges);
    }

    public Challenges getPlayerByTittle(String tittle) {
        return challengesRepository.findByTitle(tittle);
    }
}
