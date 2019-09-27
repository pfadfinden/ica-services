package de.pfadfinden.ica.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IcaGruppierungTest {

    private static IcaGruppierung icaGruppierungBund;
    private static IcaGruppierung icaGruppierungStammAktiv;
    private static IcaGruppierung icaGruppierungStammInaktiv;

    @BeforeAll
    public static void init() {
        icaGruppierungBund = new IcaGruppierung("BdP 000000",999);
        icaGruppierungStammAktiv = new IcaGruppierung("Barrakuda 020001",999);
        icaGruppierungStammInaktiv = new IcaGruppierung("zzzGoldener Reiter 060001",999);
    }

    @Test
    void getGruppierungsnummer() {
        assertEquals("BdP",icaGruppierungBund.getGruppierungsname());
        assertEquals("Barrakuda",icaGruppierungStammAktiv.getGruppierungsname());
        assertEquals("zzzGoldener Reiter",icaGruppierungStammInaktiv.getGruppierungsname());
    }

    @Test
    void getGruppierungsname() {
        assertEquals("000000",icaGruppierungBund.getGruppierungsnummer());
        assertEquals("020001",icaGruppierungStammAktiv.getGruppierungsnummer());
        assertEquals("060001",icaGruppierungStammInaktiv.getGruppierungsnummer());
    }

    @Test
    void isZzz() {
        assertFalse(icaGruppierungBund.isZzz());
        assertFalse(icaGruppierungStammAktiv.isZzz());
        assertTrue(icaGruppierungStammInaktiv.isZzz());
    }
}
