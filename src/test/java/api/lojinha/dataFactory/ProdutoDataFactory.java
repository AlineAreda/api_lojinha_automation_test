package api.lojinha.dataFactory;

import api.lojinha.pojo.ComponentePojo;
import api.lojinha.pojo.ProdutoPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProdutoDataFactory {

public static ProdutoPojo criarProduto(String payload) throws IOException {
    ObjectMapper objectMapper= new ObjectMapper();
    ProdutoPojo produto =objectMapper.readValue(new FileInputStream(payload),
            ProdutoPojo.class);
    return produto;
}

    public static ProdutoPojo cadastraProdutoPadrao() throws IOException {
        return criarProduto("src/test/resources/payloads/produto.json");
    }

    public static ProdutoPojo cadastraProdutoValorInvalido(double valor) throws IOException {
        ProdutoPojo produto = criarProduto("src/test/resources/payloads/produto.json");
        produto.setProdutoValor(valor);
        return produto;
    }

    public static ProdutoPojo cadastraNovoProduto() throws IOException {
        ProdutoPojo produto = criarProduto("src/test/resources/payloads/produto.json");
        produto.setProdutoNome("Teclado sem fio K390");
        produto.setProdutoValor(549);
        produto.setProdutoCores(cores());
        produto.setComponentes(cadastraComponente());

        return produto;
    }

    public static List<String> cores(){
        ProdutoPojo produto = new ProdutoPojo();
        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("amarelo");

        return cores;
    }

    public static List<ComponentePojo> cadastraComponente(){
        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Mouse sem fio M535");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);

        return componentes;
    }
}
