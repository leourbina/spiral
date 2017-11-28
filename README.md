# spiral

Prints matrix entries in a spiral pattern

## Building

```sh
$ lein uberjar
```

## Usage

```sh
$ java -jar uberjar/spiral-0.1.0-SNAPSHOT-standalone.jar <input-file>
```

The program expects the input file to contain a matrix as JSON.

## Testing

```sh
$ lein midje
```
