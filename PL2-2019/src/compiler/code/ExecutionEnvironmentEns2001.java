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
	private final static String[] REGISTERS = { ".PC", ".SP", ".SR", ".IX", ".IY", ".A", ".R0", ".R1", ".R2", ".R3",
			".R4", ".R5", ".R6", ".R7", ".R8", ".R9" };

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
		System.out.println(oper);
		b.append(";").append(quadruple.toString()).append(" \n");

		if (oper.equals("ADD")) {
			o1 = transOperand(quadruple.getFirstOperand());
			o2 = transOperand(quadruple.getSecondOperand());
			r = transOperand(quadruple.getResult());
			b.append("ADD " + o1 + "," + o2 + "\n");
			b.append("MOVE .A," + r);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("BR")) {
			r = transOperand(quadruple.getResult());
			b.append("BR /" + r);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("BRF")) {
			if (quadruple.getSecondOperand() != null) {
				b.append("CMP ").append(transOperand(quadruple.getFirstOperand()))
						.append(", ").append(transOperand(quadruple.getSecondOperand()))
						.append(" \n");
				} else {
					b.append("CMP #0, ").append(transOperand(quadruple.getFirstOperand())).append(" \n");
				}
				b.append("BZ /" + quadruple.getResult()).append(" \n");
				return b.toString();
		}

		if (oper.equals("CALL")) {
			r = transOperand(quadruple.getResult());
			b.append("MOVE .R0,.IX\n");
			b.append("CALL /" + r + "\n");
			b.append("MOVE .IX,.SP\n");
			b.append("MOVE .IX,.IY\n");
			b.append("MOVE #INC1[.IX],.IX");
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("STRING")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append(o1 + ": DATA " + r);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("INC")) {
			System.out.println(quadruple);
			o1 = transOperand(quadruple.getResult());
			b.append("INC ").append(o1);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("RESG")) {
			b.append("RES ").append(quadruple.getFirstOperand())
					.append(" ;Reservo memoria para variables globales y temporales \n");
			b.append("MOVE ").append(transOperand(quadruple.getSecondOperand())).append(", .IX")
					.append("  ;Situo IX en la posicion en la que va el primer temporal > globalAddress");
			b.append("\n ");
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
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("GR")) {
			LabelFactoryIF lf = new LabelFactory();
			LabelIF l1 = lf.create();
			LabelIF l2 = lf.create();
			b.append("SUB ").append(transOperand(quadruple.getFirstOperand()))
				.append(", " + transOperand(quadruple.getSecondOperand())).append("\n");
			b.append("BZ /").append(l1)
				.append("; Salto si el resultado es 0, es decir, op1 == op2 ")
				.append("\n");
			b.append("BN /")
					.append(l1)
					.append(";Salto si el resultado es negativo, es decir, op1 < op2")
					.append("\n");
			b.append("MOVE #1, ").append(transOperand(quadruple.getResult())).append("\n");
			b.append("BR /").append(l2).append("\n");
			b.append(l1).append(" : ").append("\n");
			b.append("MOVE #0, ").append(transOperand(quadruple.getResult())).append("\n");
			b.append(l2).append(" : ").append("\n");
			return b.toString();
		}

		if (oper.equals("HALT")) {
			return "HALT";
		}

		if (oper.equals("INITVAR")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("MOVE " + o1 + "," + r);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("INL")) {
			r = transOperand(quadruple.getResult());
			b.append(r + ": NOP");
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("MV")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("MOVE " + o1 + "," + r);
			b.append("\n ");
			return b.toString();
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
			b.append("\n ");
			return b.toString();	
			}

		if (oper.equals("MVP")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("MOVE " + o1 + "," + ".R0\n");
			b.append("MOVE [.R0]," + r);
			b.append("\n ");
			return b.toString();	
			}

		if (oper.equals("ORG")) {
			r = quadruple.getResult().toString();
			b.append("ORG " + r);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("PARAM")) {
			r = transOperand(quadruple.getResult());
			b.append("PUSH " + r);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("WRITESTRING")) {
			b.append("WRSTR ");
			String label = compiler.code.Label.getLabel();
			b.append("/").append(label).append(" \n");
			b.append("WRCHAR #10 ").append(" \n");
			b.append("WRCHAR #13 ").append(" \n");
			b.append(label).append(": DATA ").append("\"").append(quadruple.getFirstOperand()).append("\"").append(" \n");
			b.append("NOP \n");
			return b.toString();	
		}

		if (oper.equals("WRITEINT")) {
			if (quadruple.getResult() != null) {
				r = transOperand(quadruple.getResult());
				b.append("WRINT " + r + "\n");
			}
			b.append("WRCHAR #10");
			b.append("\n ");
			return b.toString();	
			}

		if (oper.equals("RETURN")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("MOVE " + o1 + ",[.IX]\n");
			b.append("BR /" + r);
			b.append("\n ");
			return b.toString();	
			}

		if (oper.equals("RETVALUE")) {
			r = transOperand(quadruple.getResult());
			b.append("MOVE [.IY]," + r);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("STARTMAIN")) {
			b.append("MOVE .SP,.IX\n");
			b.append("PUSH #1\n");
			b.append("PUSH .IX\n");
			b.append("PUSH .SR");
			b.append("\n ");
			return b.toString();	
			}

		if (oper.equals("STARTSUB")) {
			b.append("MOVE .SP,.R0\n");
			b.append("PUSH #1\n");
			b.append("PUSH .IX\n");
			b.append("PUSH .SR");
			b.append("\n ");
			return b.toString();	
		}

		if (oper.equals("STP")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("MOVE " + r + "," + ".R0\n");
			b.append("MOVE " + o1 + "," + "[.R0]");
			b.append("\n ");
			return b.toString();	
		}			


		if (oper.equals("NOT")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("NOT " + o1 +"\n");
			b.append("\n ");
			return b.toString();	
		}

		if (oper.equals("SUB")) {
			o1 = transOperand(quadruple.getFirstOperand());
			o2 = transOperand(quadruple.getSecondOperand());
			r = transOperand(quadruple.getResult());
			b.append("SUB " + o1 + "," + o2 + "\n");
			b.append("MOVE .A," + r);
			b.append("\n ");
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
				return "#" + v.getAddress() + "[.IX]";
			}
		}

		if (o instanceof Value) {
			return "#" + ((Value) o).getValue();
		}

		if (o instanceof Temporal) {
			return "#" + ((Temporal) o).getAddress() + "[.IX]";
		}

		if (o instanceof Label) {
			return ((Label) o).getName();
		}
		return null;
	}
}
