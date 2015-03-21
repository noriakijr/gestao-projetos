package controle.command.impl;


import modelo.Funcionario;
import modelo.Login;
import modelo.dao.IDAO;
import modelo.dao.impl.FuncionarioDAO;
import controle.command.ICommand;


public class LoginCommand implements ICommand{

	Funcionario funcionario;
	//Login login;
	
	public LoginCommand (Funcionario funcionario){
		this.funcionario = funcionario;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		IDAO dao = new FuncionarioDAO();
		dao.Pesquisar(funcionario);
		if(funcionario.getTipo_acesso() != null){
			//login = Login.getInstance();
			Login.getInstance().setId(funcionario.getId());
			Login.getInstance().setNome(funcionario.getNome());
			Login.getInstance().setSenha(funcionario.getSenha());
			Login.getInstance().setTipo_acesso(funcionario.getTipo_acesso());
		}
	}

}
