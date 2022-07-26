import com.gabrielsantos.app.dtos.ProdutoDTO;
import com.gabrielsantos.app.utils.ValidaFormularioProduto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidaformularioTest {

    @Test
    public void deveriaLancarExcecaoPoisCampoEhIgualANull() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setDescricao("Descrição - Teste");
        produtoDTO.setPreco(12.50);
        produtoDTO.setQuantidadeUnidadesEstoque(null);
        Assertions.assertThrows(Exception.class, () -> {
            new ValidaFormularioProduto().validarFormulario(produtoDTO);
        });
    }
    @Test
    public void deveriaLancarExcecaoPoisCampoEhUmaStringVazia() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setDescricao("");
        produtoDTO.setPreco(12.50);
        produtoDTO.setQuantidadeUnidadesEstoque(1200);
        Assertions.assertThrows(Exception.class, () -> {
            new ValidaFormularioProduto().validarFormulario(produtoDTO);
        });
    }
    @Test
    public void deveriaLancarExcecaoPoisQuantidadeUnidadesEmEstoqueEhMenorQueZero() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setDescricao("Teste");
        produtoDTO.setPreco(12.50);
        produtoDTO.setQuantidadeUnidadesEstoque(-1);
        Assertions.assertThrows(Exception.class, () -> {
            new ValidaFormularioProduto().validarFormulario(produtoDTO);
        });
    }
    @Test
    public void deveriaLancarExcecaoPoisPrecoDoProdutoEhMenorQueZero() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setDescricao("Teste");
        produtoDTO.setPreco(-1.0);
        produtoDTO.setQuantidadeUnidadesEstoque(1200);
        Assertions.assertThrows(Exception.class, () -> {
            new ValidaFormularioProduto().validarFormulario(produtoDTO);
        });
    }
    @Test
    public void deveriaRetornarTruePoisTodosOsCamposSaoValidos() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setDescricao("Teste");
        produtoDTO.setPreco(1000.89);
        produtoDTO.setQuantidadeUnidadesEstoque(1200);
        Assertions.assertEquals(true, new ValidaFormularioProduto().validarFormulario(produtoDTO));
    }
}
