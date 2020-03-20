package Controller;

import DAO.DAO;
import sistema.entidades.Cargo;

public class ControllerCargo {
	
	DAO dao = new DAO();
	public boolean inserirCargoController(Cargo pCargo){
		System.out.println("Controller");
		return this.dao.inserirCargo(pCargo);
	}
}
