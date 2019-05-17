package compiler.code;

import compiler.intermediate.Label;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelFactoryIF;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.VariableIF;

import java.util.Arrays;
import java.util.List;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 implements ExecutionEnvironmentIF {
	private final static int MAX_ADDRESS = 65535;
	private final static String[] REGISTERS = {
			".PC", ".SP", ".SR", ".IX", ".IY", ".A",
			".R0", ".R1", ".R2", ".R3", ".R4",
			".R5", ".R6", ".R7", ".R8", ".R9"
	};

	private RegisterDescriptorIF registerDescriptor;
	private MemoryDescriptorIF memoryDescriptor;

	/**
	 * Constructor for ENS2001Environment.
	 */
	public ExecutionEnvironmentEns2001() {
		super();
	}

	/**
	 * Returns the size of the type within the architecture.
	 *
	 * @return the size of the type within the architecture.
	 */
	@Override
	public final int getTypeSize(TypeSimple type) {
		return 1;
	}

	/**
	 * Returns the registers.
	 *
	 * @return the registers.
	 */
	@Override
	public final List<String> getRegisters() {
		return Arrays.asList(REGISTERS);
	}

	/**
	 * Returns the memory size.
	 *
	 * @return the memory size.
	 */
	@Override
	public final int getMemorySize() {
		return MAX_ADDRESS;
	}

	/**
	 * Returns the registerDescriptor.
	 *
	 * @return Returns the registerDescriptor.
	 */
	@Override
	public final RegisterDescriptorIF getRegisterDescriptor() {
		return registerDescriptor;
	}

	/**
	 * Returns the memoryDescriptor.
	 *
	 * @return Returns the memoryDescriptor.
	 */
	@Override
	public final MemoryDescriptorIF getMemoryDescriptor() {
		return memoryDescriptor;
	}

	/**
	 * Translate a quadruple into a set of final code instructions.
	 *
	 * @param cuadruple The quadruple to be translated.
	 * @return a quadruple into a set of final code instructions.
	 */
	@Override
	public final String translate(QuadrupleIF quadruple) {
		String o1;
		String o2;
		String r;
		String oper = quadruple.getOperation();
		StringBuffer b = new StringBuffer();

		if (oper.equals("ADD")) {
			o1 = transOperand(quadruple.getFirstOperand());
			o2 = transOperand(quadruple.getSecondOperand());
			r = transOperand(quadruple.getResult());
			b.append("ADD " + o1 + "," + o2 + "\n");
			b.append("MOVE .A," + r);
			return b.toString();
		}

		if (oper.equals("BR")) {
			r = transOperand(quadruple.getResult());
			return "BR /" + r;
		}

		if (oper.equals("BRF")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("CMP " + r + ",#0\n");
			b.append("BZ /" + o1);
			return b.toString();
		}

		if (oper.equals("CALL")) {
			r = transOperand(quadruple.getResult());
			b.append("MOVE .R0,.IX\n");
			b.append("CALL /" + r + "\n");
			b.append("MOVE .IX,.SP\n");
			b.append("MOVE .IX,.IY\n");
			b.append("MOVE #INC-1[.IX],.IX");
			return b.toString();
		}

		if (oper.equals("STRING")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			return o1 + ": DATA " + r;
		}

		if (oper.equals("INC")) {
			o1 = transOperand(quadruple.getFirstOperand());
			return "INC " + o1;
		}

		if (oper.equals("ENDMAIN")) {
			r = transOperand(quadruple.getResult());
			b.append(r).append(": NOP\n").append("MOVE .IX, .SP").toString();
		}

		if (oper.equals("ENDSUB")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append(r + ": NOP\n");
			b.append("SUB .IX," + o1 + "\n");
			b.append("MOVE .A,.SP\n");
			b.append("RET");
			return b.toString();
		}

		if (oper.equals("EQ")) {
			LabelFactoryIF lf = new LabelFactory();
			LabelIF l1 = lf.create();
			LabelIF l2 = lf.create();
			o1 = transOperand(quadruple.getFirstOperand());
			o2 = transOperand(quadruple.getSecondOperand());
			r = transOperand(quadruple.getResult());
			b.append("CMP " + o1 + "," + o2 + "\n");
			b.append("BZ /" + l1 + "\n");
			b.append("MOVE #0," + r + "\n");
			b.append("BR /" + l2 + "\n");
			b.append(l1 + ": MOVE #1," + r + "\n");
			b.append(l2 + ": NOP");
			return b.toString();
		}

		if (oper.equals("GR")) {
			LabelFactoryIF lf = new LabelFactory();
			LabelIF l1 = lf.create();
			LabelIF l2 = lf.create();
			o1 = transOperand(quadruple.getFirstOperand());
			o2 = transOperand(quadruple.getSecondOperand());
			r = transOperand(quadruple.getResult());
			b.append("CMP " + o2 + "," + o1 + "\n");
			b.append("BN /" + l1 + "\n");
			b.append("MOVE #0," + r + "\n");
			b.append("BR /" + l2 + "\n");
			b.append(l1 + ": MOVE #1," + r + "\n");
			b.append(l2 + ": NOP");
			return b.toString();
		}

		if (oper.equals("HALT")) {
			return "HALT";
		}

		if (oper.equals("INITVAR")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			return "MOVE " + o1 + "," + r;
		}

		if (oper.equals("INL")) {
			r = transOperand(quadruple.getResult());
			return r + ": NOP";
		}

		if (oper.equals("MV")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			return "MOVE " + o1 + "," + r;
		}

		if (oper.equals("MVA")) {
			OperandIF o = quadruple.getFirstOperand();
			r = transOperand(quadruple.getResult());
			VariableIF var = (Variable) o;
			if (var.isGlobal()) {
				String vadd = ((Integer) var.getAddress()).toString();
				b.append("MOVE #" + vadd + "," + r);
			} else {
				b.append("SUB .IX,#" + var.getAddress() + "\n");
				b.append("MOVE .A," + r);
			}
			return b.toString();
		}

		if (oper.equals("MVP")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("MOVE " + o1 + "," + ".R0\n");
			b.append("MOVE [.R0]," + r);
			return b.toString();
		}

		if (oper.equals("ORG")) {
			r = quadruple.getResult().toString();
			return "ORG " + r;
		}

		if (oper.equals("PARAM")) {
			r = transOperand(quadruple.getResult());
			return "PUSH " + r;
		}

		if (oper.equals("POINTERSUB")) {
			r = transOperand(quadruple.getResult());
			b.append("SUB .IX," + r + "\n");
			b.append("MOVE .A,.SP");
			return b.toString();
		}

		if (oper.equals("WRITESTRING")) {
			r = transOperand(quadruple.getResult());
			b.append("WRSTR /" + r + "\n");
			b.append("WRCHAR #10");
			return b.toString();
		}

		if (oper.equals("WRITEINT")) {
			if (quadruple.getResult() != null) {
				r = transOperand(quadruple.getResult());
				b.append("WRINT " + r + "\n");
			}
			b.append("WRCHAR #10");
			return b.toString();
		}

		if (oper.equals("RETURN")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("MOVE " + o1 + ",[.IX]\n");
			b.append("BR /" + r);
			return b.toString();
		}

		if (oper.equals("RETVALUE")) {
			r = transOperand(quadruple.getResult());
			return "MOVE [.IY]," + r;
		}

		if (oper.equals("STARTMAIN")) {
			b.append("MOVE .SP,.IX\n");
			b.append("PUSH #-1\n");
			b.append("PUSH .IX\n");
			b.append("PUSH .SR");
			return b.toString();
		}

		if (oper.equals("STARTSUB")) {
			b.append("MOVE .SP,.R0\n");
			b.append("PUSH #-1\n");
			b.append("PUSH .IX\n");
			b.append("PUSH .SR");
			return b.toString();
		}

		if (oper.equals("STP")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("MOVE " + r + "," + ".R0\n");
			b.append("MOVE " + o1 + "," + "[.R0]");
			return b.toString();
		}

		if (oper.equals("SUB")) {
			o1 = transOperand(quadruple.getFirstOperand());
			o2 = transOperand(quadruple.getSecondOperand());
			r = transOperand(quadruple.getResult());
			b.append("SUB " + o1 + "," + o2 + "\n");
			b.append("MOVE .A," + r);
			return b.toString();
		}

		return quadruple.toString();
	}

	private String transOperand(OperandIF o) {

		if (o instanceof Variable) {
			VariableIF v = (Variable) o;
			if (v.isGlobal()) {
				return "/" + v.getAddress();
			} else {
				return "#-" + v.getAddress() + "[.IX]";
			}
		}

		if (o instanceof Value) {
			return "#" + ((Value) o).getValue();
		}

		if (o instanceof Temporal) {
			return "#-" + ((Temporal) o).getAddress() + "[.IX]";
		}

		if (o instanceof Label) {
			return ((Label) o).getName();
		}
		return null;
	}
}
