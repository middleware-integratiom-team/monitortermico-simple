package distribution;

import aplication.Medicao;
import aplication.TipoGrandeza;

public interface IClientProxy {

	public Medicao getMedicao(TipoGrandeza tipo);
	
	public void setMedicao (Medicao m);
}
