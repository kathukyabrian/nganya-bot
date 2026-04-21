package tech.kitucode.telegram.repository;

import tech.kitucode.telegram.domain.Nganya;

import java.util.List;

public class NganyaRepository {
    public static List<Nganya> findAll() {
        Nganya mood = new Nganya(1L, "Mood", "", "");
        Nganya fest = new Nganya(2L, "Money Fest", "", "");
        Nganya brawl = new Nganya(3L, "Brawl Out", "", "");
        Nganya mellows = new Nganya(4L, "Mellows", "", "");
        Nganya babayaga = new Nganya(5L, "Babayaga", "", "");
        return List.of(mood, fest, brawl, mellows, babayaga);
    }
}
