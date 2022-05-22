package api.lojinha.dataFactory;


import api.lojinha.pojo.UsuarioPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.io.FileInputStream;
import java.io.IOException;


public class UsuarioDataFactory {

    static Faker faker = new Faker();


    public static UsuarioPojo adicionaUsuarioJson(String payload) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UsuarioPojo usuario = objectMapper.readValue(new FileInputStream(payload),UsuarioPojo.class);
        return usuario;
    }

    public static UsuarioPojo adicionaUsuario() throws IOException {
        UsuarioPojo usuario = adicionaUsuarioJson("src/test/resources/payloads/usuario.json");
        return usuario;
    }

    public static UsuarioPojo adicionaNovoUsuario() throws IOException {
        UsuarioPojo usuario = adicionaUsuarioJson("src/test/resources/payloads/usuario.json");
        usuario.setUsuarioNome("Ana " + faker.name().lastName());
        usuario.setUsuarioLogin("ana" + faker.name().username());
        usuario.setUsuarioSenha("ana" +faker.name().suffix());
        return usuario;
    }



    public static UsuarioPojo loginUsuarioAdministrador(){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha("admin");

        return usuario;
    }

    public static UsuarioPojo loginUsuario(){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("alineareda");
        usuario.setUsuarioSenha("a123456");

        return usuario;
    }

    public static UsuarioPojo loginUsuarioSemCadastro(){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("naoCadastrado");
        usuario.setUsuarioSenha("naoCadastrada");

        return usuario;
    }

}
