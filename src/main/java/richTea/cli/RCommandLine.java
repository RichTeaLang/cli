package richTea.cli;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import richTea.runtime.attribute.AttributeSet;
import richTea.runtime.attribute.PrimativeAttribute;
import richTea.runtime.execution.AbstractFunction;
import richTea.runtime.node.TreeNode;

public class RCommandLine extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		String[] arguments = getArguments();
		Options options = getOptions();
		AttributeSet returns = new AttributeSet();
		
		try {
			CommandLine cli = new DefaultParser().parse(options, arguments);
			
			for(Option option : cli.getOptions()) {
				returns.setAttribute(new PrimativeAttribute(option.getOpt(), option));
			}
		} catch (ParseException e) {
			new HelpFormatter().printHelp("java -jar <app>", options, true);
			
			for(Option option : options.getOptions()) {
				returns.setAttribute(new PrimativeAttribute(option.getOpt(), option));
			}
			
			context.executeBranch("parseError", returns.getAttributes());
		}
		
		context.setLastReturnValue(returns);
	}
	
	protected String[] getArguments() {
		@SuppressWarnings("unchecked")
		List<String> args = (List<String>) context.getValue("args");
		
		return args.toArray(new String[args.size()]);
	}
	
	protected Options getOptions() {
		Options options = new Options();
		TreeNode[] optionNodes = context.getCurrentNode().getBranch("options").getChildren();
		
		for(TreeNode node : optionNodes) {
			options.addOption(((ROption) node).getOption());
		}
		
		return options;
	}
}
