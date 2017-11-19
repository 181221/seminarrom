package no.pk.util;

import no.pk.model.Hendelse;
import no.pk.model.Rom;

import java.util.ArrayList;

public class ReaderHjelp {
    private ArrayList<Rom> allerom;

    public ReaderHjelp() {
        allerom = new ArrayList<>();
    }

    public void setOppData(String[] felt) {
        String dato = felt[0];
        String start = felt[1].substring(1);
        String slutt = felt[3].substring(1);
        String romnavn = parseRomNavn(felt[5].substring(1));
        Hendelse h = lagHendelse(dato, start, slutt);
        if (!inneholder(romnavn)) {
            Rom r = lagRom(romnavn);
            r.getHendelser().add(h);
            allerom.add(r);
        } else {
            finnRomOgLeggTil(romnavn, h);
        }
    }

    public boolean inneholder(String r) {
        for (int i = 0; i < allerom.size(); i++) {
            if (allerom.get(i).getNavn().equals(r)) {
                return true;
            }
        }
        return false;
    }

    public void finnRomOgLeggTil(String r, Hendelse h) {
        for (int i = 0; i < allerom.size(); i++) {
            Rom rom = allerom.get(i);
            if (rom.getNavn() != null) {
                if (r.equals(rom.getNavn())) {
                    allerom.get(i).getHendelser().add(h);
                }
            }
        }
    }

    public String parseData(String data) {
        String dataString = data;
        switch (data) {
            case "romnavn":
                dataString = parseRomNavn(data);
                break;
        }
        return dataString;

    }

    public String parseRomNavn(String rom) {
        if (rom.contains("\"")) {
            rom = rom.replace('"', ' ').substring(1);
        }
        if (rom.charAt(0) == ' ') {
            rom = rom.substring(1);
        }
        return rom;
    }

    public Hendelse lagHendelse(String dato, String start, String slutt) {
        Hendelse h = new Hendelse();
        h.setDato(dato);
        h.setStart(start);
        h.setSlutt(slutt);
        return h;
    }

    public static Rom lagRom(String romnavn) {
        Rom r = new Rom();
        r.setNavn(romnavn);
        return r;
    }

    public ArrayList<Rom> getAllerom() {
        return allerom;
    }

    public void setAllerom(ArrayList<Rom> allerom) {
        this.allerom = allerom;
    }


}
