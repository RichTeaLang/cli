# RichTea-Cli Module

RichTea module which wraps the [commons-cli](https://commons.apache.org/proper/commons-cli/) java library.
 
# Example

```
Import("*" from:"path/to/cli_module");

Let(cli:@CommandLine({
  Option("in" desc:"Input file" numberOfArgs:1 required:true)
  Option("debug" desc:"Debug mode")
} parseError {
  SystemExit(1)
}))

Print("In = { cli.in.value }, Debug = { cli.debug.value }")
```


