package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class StmSubprogram extends NonTerminal {

	private List<CabProcedure> procedures;

	public StmSubprogram() {
		super();
		procedures = new ArrayList<>();
	}

	public void addCuerpoAndProcedure(Cuerpo cuerpo, CabProcedure cabProcedure) {
		cabProcedure.addCuerpo(cuerpo);
		procedures.add(cabProcedure);
	}

	public List<CabProcedure> getProcedures(){
		return procedures;
	}

	@Override
	public String toString() {
		return "StmSubprogram{" +
				"procedures=" + procedures +
				'}';
	}
}