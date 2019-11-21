package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int taulukonKasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkmTaulukossa;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    /*
    poista copypaste
    anna muuttujille selkeät nimet
    tee metodeista pienempiä ja hyvän koheesion omaavia    
     */
    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        alkioidenLkmTaulukossa = 0;
        this.taulukonKasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        alkioidenLkmTaulukossa = 0;
        this.taulukonKasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        lukujono = new int[kapasiteetti];
        alkioidenLkmTaulukossa = 0;
        this.taulukonKasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {

        if (kuuluu(luku)) {
            return false;
        }

        if (alkioidenLkmTaulukossa == lukujono.length) {
            int[] taulukkoOld = new int[lukujono.length];
            taulukkoOld = lukujono;
            lukujono = new int[alkioidenLkmTaulukossa + taulukonKasvatuskoko];
            kopioiTaulukko(taulukkoOld, lukujono);
        }

        lukujono[alkioidenLkmTaulukossa] = luku;
        alkioidenLkmTaulukossa++;
        return true;

    }

    public boolean kuuluu(int luku) {
        boolean onkoLukuaTaulukossa = false;
        for (int i = 0; i < alkioidenLkmTaulukossa; i++) {
            if (luku == lukujono[i]) {
                onkoLukuaTaulukossa = true;
            }
        }
        return onkoLukuaTaulukossa;

    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLkmTaulukossa; i++) {
            if (luku == lukujono[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                lukujono[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkmTaulukossa - 1; j++) {
                apu = lukujono[j];
                lukujono[j] = lukujono[j + 1];
                lukujono[j + 1] = apu;
            }
            alkioidenLkmTaulukossa--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkmTaulukossa;
    }

    @Override
    public String toString() {
        if (alkioidenLkmTaulukossa == 0) {
            return "{}";
        }
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkmTaulukossa - 1; i++) {
            tuotos += lukujono[i];
            tuotos += ", ";
        }
        tuotos += lukujono[alkioidenLkmTaulukossa - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkmTaulukossa];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
