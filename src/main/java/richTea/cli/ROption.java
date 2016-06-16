package richTea.cli;

import org.apache.commons.cli.Option;

import richTea.runtime.node.DataNode;

public class ROption extends DataNode {
	
	public Option getOption() {
		return Option.builder(resolver.getString("opt"))
			.argName(resolver.getString("argName"))
			.desc(resolver.getString("desc"))
			.longOpt(resolver.getString("longOpt"))
			.numberOfArgs((int) resolver.getNumberOrDefault("numberOfArgs", 0))
			.required(resolver.getBooleanOrDefault("required", true))
			.build();
	}
}
