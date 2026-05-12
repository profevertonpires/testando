package testando;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();

    // GET
    @GetMapping
    public List<Produto> listarProdutos() {

        produtos.add(new Produto(Long.parseLong("1"), "produto1", 22));
        produtos.add(new Produto(Long.parseLong("2"), "produto2", 13));
        
        return produtos;
    }

    // POST
    @PostMapping
    public String adicionarProduto(@RequestBody Produto produto) {
        produtos.add(produto);
        return "Produto adicionado com sucesso!";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String removerProduto(@PathVariable Long id) {

        produtos.removeIf(produto -> produto.getId().equals(id));

        return "Produto removido com sucesso!";
    }

}
