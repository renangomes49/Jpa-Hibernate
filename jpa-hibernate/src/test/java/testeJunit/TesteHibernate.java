package testeJunit;

import java.util.List;

import org.junit.Test;

import conexao.HibernateUtil;
import dao.DaoGenerico;
import model.Pessoa;
import model.TelefonePessoa;

public class TesteHibernate {

	@Test	
	public void testeConexaoHibernateUtil() {
		
	HibernateUtil.getEntityManager();
		
	}
	
	@Test	
	public void testeSalvarDaoGenerico() {
		
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		Pessoa pessoa = new Pessoa("Antonio", "Egidio", "login", "egidio@email.com", "123", 18);
		
		daoGenerico.salvar(pessoa);
	}
	

	@Test	
	public void testeConsultarDaoGenerico() {
		
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(2L);
		
		pessoa = daoGenerico.consultar(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test	
	public void testeAtualizarDaoGenerico() {
		
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(2L);
		pessoa = daoGenerico.consultar(pessoa);
		 
		pessoa.setIdade(30);
		pessoa.setNome("Nome Atualizado");
		System.out.println(daoGenerico.updateMerge(pessoa));
		
	}
	
	@Test	
	public void testeDeleteDaoGenerico() {
		
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1L);
		
		daoGenerico.deletar(pessoa);
		
	}
	
	@Test	
	public void testeListarPessoasDaoGenerico() {
		
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		List<Pessoa> list = daoGenerico.listar(Pessoa.class);
		
		for (Pessoa pessoa : list) {
			System.out.println(pessoa);
			System.out.println("_____________________________________________");
		}
		
	}
	
	@Test	
	public void testeQueryList() {
		
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		List<Pessoa> list = daoGenerico.getEntityManager().createQuery("from Pessoa where id = 4").getResultList();
		
		for (Pessoa pessoa : list) {
			System.out.println(pessoa);
		}
		
	}
	
	@Test	
	public void testeQueryListMaxResult() {
		
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		List<Pessoa> list = daoGenerico.getEntityManager().createQuery("from Pessoa order by nome")
				.setMaxResults(4)
				.getResultList();
		
		for (Pessoa pessoa : list) {
			System.out.println(pessoa);
		}
		
	}
	
	@Test
	public void testeQueryListParameter() {
	
	DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		List<Pessoa> list = daoGenerico.getEntityManager().createQuery("from Pessoa where nome = :nome")
				.setParameter("nome", "Antonio")
				.getResultList();
		
		for (Pessoa pessoa : list) {
			System.out.println(pessoa);
		}
	
		
	}
	
	@Test
	public void testeQuerySomaIdade() {
	
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		Long somaIdade = (Long) daoGenerico.getEntityManager().createQuery("select sum(p.idade) from Pessoa p")
				.getSingleResult();
	
		System.out.println(somaIdade);
	}

	@Test
	public void testeNamedQuery1() {
	
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		List<Pessoa> list =  daoGenerico.getEntityManager().createNamedQuery("Pessoa.findAll").getResultList(); 
		
		for (Pessoa pessoa : list) {
			System.out.println(pessoa);
		}
	}
	
	@Test
	public void testeNamedQuery2() {
	
		DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
		
		List<Pessoa> list =  daoGenerico.getEntityManager().createNamedQuery("Pessoa.findByName")
				.setParameter("nome", "Bernardo")
				.getResultList(); 
		
		for (Pessoa pessoa : list) {
			System.out.println(pessoa);
		}
	}
	
	@Test
	public void testeGravarPessoaComTelefone() {
	
		DaoGenerico daoGenerico = new DaoGenerico<>();
		
		Pessoa pessoa = new Pessoa("Fernando", "Lima", "login", "fe@gmail.com", "123", 40);
		
		daoGenerico.salvar(pessoa);
		
		TelefonePessoa telefonePessoa = new TelefonePessoa();
		telefonePessoa.setTipo("celular");
		telefonePessoa.setNumero("00 0 0000 0000");
		telefonePessoa.setPessoa(pessoa);
		
		
		daoGenerico.salvar(telefonePessoa);
	}
	
	@Test
	public void testeConsultaPessoaComTelefone() {
	
		DaoGenerico daoGenerico = new DaoGenerico<>();
	
		Pessoa pessoa = (Pessoa) daoGenerico.consultar2(9L, Pessoa.class);
		
		for (TelefonePessoa telefone : pessoa.getTelefoneUsers()) {
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getPessoa().getNome());
		}
	}
}




