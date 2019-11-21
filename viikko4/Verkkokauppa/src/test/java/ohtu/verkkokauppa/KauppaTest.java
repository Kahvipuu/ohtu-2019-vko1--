package ohtu.verkkokauppa;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

//    private Pankki pankki;
    //  private Viitegeneraattori viite;
    //private Varasto varasto;
    @Before
    public void setUp() {
        //Ei toimi
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // Pankki -> public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki, times(1)).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(5));
        // toistaiseksi välitetään taas  //toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void useanEriTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        Kauppa k = new Kauppa(varasto, pankki, viite);
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "papu", 1));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("jaska", "54321");
        verify(pankki).tilisiirto(eq("jaska"), anyInt(), eq("54321"), anyString(), eq(6));
    }

    @Test
    public void useanSamanTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        Kauppa k = new Kauppa(varasto, pankki, viite);
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "papu", 1));

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("jokunen", "54321");
        verify(pankki).tilisiirto(eq("jokunen"), anyInt(), eq("54321"), anyString(), eq(2));
    }

    @Test
    public void loppuneenTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        Kauppa k = new Kauppa(varasto, pankki, viite);
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(0);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "papu", 1));

        k.aloitaAsiointi();

        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("maidoton", "58858");
        verify(pankki).tilisiirto(eq("maidoton"), anyInt(), eq("58858"), anyString(), eq(1));
    }

    @Test
    public void asioinninAloittaminenNollaaOstoskorin() {

        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        Kauppa k = new Kauppa(varasto, pankki, viite);
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "papu", 1));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("maidoton", "58858");
        verify(pankki).tilisiirto(eq("maidoton"), anyInt(), eq("58858"), anyString(), eq(1));
    }

    @Test
    public void uuusiViitenumeroJokaMaksutapahtumalla() {

        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).
                thenReturn(1).
                thenReturn(2).
                thenReturn(3);

        Varasto varasto = mock(Varasto.class);
        Kauppa k = new Kauppa(varasto, pankki, viite);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "papu", 1));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("maidoton", "58858");
        verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("maidoton", "58858");
        verify(pankki).tilisiirto(anyString(), eq(2), anyString(), anyString(), anyInt());

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("maidoton", "58858");
        verify(pankki).tilisiirto(anyString(), eq(3), anyString(), anyString(), anyInt());
    }

    @Test
    public void poistaKorista() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        Kauppa k = new Kauppa(varasto, pankki, viite);
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "papu", 1));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(1);
        
        k.tilimaksu("maidoton", "58858");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(1));
        
        
    }

}
