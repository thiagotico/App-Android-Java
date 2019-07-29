package com.escolaamericana;

public final class  Session {
    private static Usuario usuario;

    public Session(Usuario usuario) {
        this.usuario = usuario;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Session.usuario = usuario;
    }
}
