package compiler.code;

import compiler.intermediate.Procedure;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolFunction;
import compiler.semantic.symbol.SymbolProcedure;
import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelFactoryIF;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

import java.util.Arrays;
import java.util.List;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 implements ExecutionEnvironmentIF {
	private final static int MAX_ADDRESS = 65535;

	private final static String[] REGISTERS = {".PC", ".SP", ".SR", ".IX", ".IY", ".A", ".R0", ".R1", ".R2", ".R3",
			".R4", ".R5", ".R6", ".R7", ".R8", ".R9"};

	private RegisterDescriptorIF registerDescriptor;
	private MemoryDescriptorIF memoryDescriptor;

	public static int scope = 0;

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
		b.append(";").append(quadruple.toString()).append(" \n");

		if (oper.equals("MULT")) {
			o1 = transOperand(quadruple.getFirstOperand());
			o2 = transOperand(quadruple.getSecondOperand());
			r = transOperand(quadruple.getResult());
			b.append("MUL " + o1 + "," + o2 + "\n");
			b.append("MOVE .A," + r);
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("BR")) {
			b.append("BR /" + quadruple.getResult());
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

		if (oper.equals("END")) {
			b.append("END");
			return b.toString();
		}

		if (oper.equals("ENDMAIN")) {
			r = transOperand(quadruple.getResult());
			b.append(r + ": NOP\n");
			b.append("MOVE .IX, .SP");
			return b.toString();
		}

		if (oper.equals("CALL")) {
			b.append("MOVE .R0,.IX\n");
			b.append("CALL /L_"+ quadruple.getResult().toString()  + "\n");
			b.append("MOVE .IX,.SP\n");
			b.append("MOVE #-3[.IX],.R0\n");
			b.append("MOVE .R0,.IX\n");
			return b.toString();
		}

		if (oper.equals("POINTERRA")) {
			r = transOperand(quadruple.getFirstOperand());
			b.append("SUB .IX, "+r+"\n");
			b.append("MOVE .A, .SP\n");
			return b.toString();
		}

		if (oper.equals("ENDPOINTERRA")) {
			o1= transOperand(quadruple.getFirstOperand());
			b.append(quadruple.getResult() + ": NOP\n");
			b.append("SUB .IX, "+o1+"\n");
			b.append("MOVE .A, .SP\n");
			b.append("RET\n");
			return b.toString();
		}

		if (oper.equals("SUBPROGRAMPARAM")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append(o1 + ": DATA " + r);
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

		if (oper.equals("HALT")) {
			b.append("HALT");
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("ORG")) {
			r = quadruple.getResult().toString();
			return "ORG " + r;
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
			b.append("BZ /").append(l1).append("\n");
			b.append("BN /").append(l1).append("\n");
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
			b.append(quadruple.getResult()+ ": NOP");
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("MV")) {
			b.append("MOVE ");
			b.append(transOperand(quadruple.getFirstOperand()));
			b.append(", ");
			b.append(transOperand(quadruple.getResult()));
			b.append(" \n");
			return b.toString();
		}

		if (oper.equals("MVA")) {
			String op1 = null;
			boolean IY = false;
			if (quadruple.getFirstOperand() instanceof Variable) {
				Variable v = (Variable)quadruple.getFirstOperand();
				if (v.getScope().getLevel() != scope) {
					if (v.getScope().getLevel() == 0) {
						op1 = transOperand(quadruple.getFirstOperand());
					} else {
						IY  = true;
						b.append("MOVE ").append("[.IY]").append(", ").append(".IY \n");

						if (scope - v.getScope().getLevel() > 1) {
							int level = v.getScope().getLevel() +1;
							while (level < scope) {
								b.append("MOVE ").append("[.IY]").append(", ").append(".IY \n");
								level++;
							}
						}
						op1 = transOperand(quadruple.getFirstOperand());
					}
				} else {
					op1 = transOperand(quadruple.getFirstOperand());
				}
			} else {
				op1 = transOperand(quadruple.getFirstOperand());
			}
			
			b.append("MOVE ");
			b.append(op1);
			b.append(", ");
			b.append(transOperand(quadruple.getResult()));
			b.append("\n");
			if (IY) {
				b.append("; Restauro IY ").append("\n");
				b.append("MOVE .IX, .IY \n");
			}
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

		if (oper.equals("DATA")) {
			b.append("RES ").append(quadruple.getFirstOperand()).append("\n");
			b.append("MOVE ").append(transOperand(quadruple.getSecondOperand())).append(", .IX");
			b.append("\n ");

			return b.toString();
		}

		if (oper.equals("STARTRA")) {
			b.append("MOVE .SP,.R0\n");
			b.append("PUSH #-1\n");
			b.append("PUSH .SR\n");
			b.append("PUSH .IX\n");
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
			b.append(label).append(": DATA ").append("\"").append(quadruple.getFirstOperand()).append("\"")
					.append(" \n");
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

		if (oper.equals("ADD")) {
			b.append("ADD ").append(transOperand(quadruple.getFirstOperand())).append(", ")
				.append(transOperand(quadruple.getSecondOperand())).append(" \n");
			b.append("MOVE .A, ").append(transOperand(quadruple.getResult()))
				.append(" \n");
			return b.toString();
		}

		if (oper.equals("RETURN")) {
			o1 = transOperand(quadruple.getFirstOperand());
			b.append("MOVE " + o1 + ",[.IX]\n");
			b.append("BR /" + quadruple.getResult()).append(" \n");
			return b.toString();
		}

		if (oper.equals("RETVALUE")) {
			r = transOperand(quadruple.getResult());
			b.append("MOVE [.IY]," + r).append(" \n");
			return b.toString();
		}

		if (oper.equals("STARTFUNC")) {
			b.append("MOVE .SP,.R0\n");
			b.append("PUSH #1\n");
			b.append("PUSH .IX\n");
			b.append("PUSH .SR");
			b.append("\n ");
			return b.toString();
		}

		if (oper.equals("NOT")) {
			o1 = transOperand(quadruple.getFirstOperand());
			r = transOperand(quadruple.getResult());
			b.append("NOT " + o1 + "\n");
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

		if (o instanceof Procedure) {
			return "/" + ((Procedure)o).getCodeLabel();
		} if (o instanceof Variable) {
			Variable v = (Variable) o;
			int dirVar = v.getAddress() + v.getOffset();
			if (v.isGlobal()) {	
				return "/" + dirVar;
			} else if (!v.isParameter()) {
				if (v.getScope().getLevel() == scope) {
					return "#" + dirVar + "[.IX]";
				} else {
					dirVar = dirVar + v.getEnclosingSymbol().getSize() + 4;
					return "#" + dirVar + "[.IX]";
				}
			} else if (v.isParameter()) {
				if (v.getScope().getLevel() == scope) {
					return "#"
							+ (v.getEnclosingSymbol().getSize() + 2
									- v.getEnclosingSymbol().getParamSize() + dirVar + 1)
							+ "[.IX]";
				} else {
					dirVar = dirVar + v.getEnclosingSymbol().getSize() + 4;
					return "#-" + (dirVar + 1)  + "[.IY]";
				}
			}
		} else if (o instanceof Temporal) {
			Temporal t = (Temporal) o;
			return "#" + t.getAddress() + "[.IX]";
		} else if (o instanceof Value) {
			Value v = (Value) o;
			if (v.getValue().toString().equals("true")) {
				return "#1";
			} else if (v.getValue().toString().equals("false")) {
				return "#0";
			} else if (v.getValue() instanceof String) {
				return v.getValue().toString();
			}

			return "#" + v.getValue();
		}

		return null;
	}
}
