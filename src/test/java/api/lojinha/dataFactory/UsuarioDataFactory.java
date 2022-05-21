package api.lojinha.dataFactory;

import api.lojinha.pojo.UsuarioPojo;

public class UsuarioDataFactory {

    public static UsuarioPojo LoginUsuarioAdministrador(){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha("admin");

        return usuario;
    }

    public static UsuarioPojo LoginCredencialIncorreta(){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("ana");
        usuario.setUsuarioSenha("user123");

        return usuario;
    }
}
