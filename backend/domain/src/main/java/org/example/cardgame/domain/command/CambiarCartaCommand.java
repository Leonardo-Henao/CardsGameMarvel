package org.example.cardgame.domain.command;

import co.com.sofka.domain.generic.Command;
import org.example.cardgame.domain.values.Carta;
import org.example.cardgame.domain.values.JuegoId;
import org.example.cardgame.domain.values.JugadorId;

public class CambiarCartaCommand extends Command {

    private JuegoId juegoId;

    private JugadorId jugadorId;

    private Carta cardOld;

    private Carta cardNew;

    public void setJuegoId(JuegoId juegoId) {
        this.juegoId = juegoId;
    }

    public void setJugadorId(JugadorId jugadorId) {
        this.jugadorId = jugadorId;
    }

    public void setCardOld(Carta cardOld) {
        this.cardOld = cardOld;
    }

    public void setCardNew(Carta cardNew) {
        this.cardNew = cardNew;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Carta getCardOld() {
        return cardOld;
    }

    public Carta getCardNew() {
        return cardNew;
    }
}
