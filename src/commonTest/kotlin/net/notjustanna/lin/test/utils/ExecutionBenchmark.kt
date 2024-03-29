package net.notjustanna.lin.test.utils

import net.notjustanna.lin.Lin
import net.notjustanna.lin.bytecode.CompiledSource
import net.notjustanna.lin.compiler.NodeCompiler
import net.notjustanna.lin.vm.LinVirtualMachine
import net.notjustanna.lin.vm.types.LAny
import net.notjustanna.tartar.api.lexer.Source
import net.notjustanna.unifiedplatform.currentPlatform
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
class ExecutionBenchmark(private val source: Source, inputValues: List<LAny> = emptyList()) {
    val result: LAny
    val input: MutableList<LAny>
    val output: MutableList<LAny>

    private val lexerDuration: Duration
    private val parseDuration: Duration
    private val compileDuration: Duration
    private val executionDuration: Duration
    private val serializeDuration: Duration
    private val deserializeDuration: Duration

    init {
        val (tokens, lexerDuration) = measureTimedValue { Lin.parser.lexer.parseToList(source) }
        val (node, parseDuration) = measureTimedValue { Lin.parser.parser.parse(source, tokens) }
        val (compiledSource, compileDuration) = measureTimedValue { NodeCompiler.compile(node) }
        val (bytes, serializeDuration) = measureTimedValue { compiledSource.toBytes() }
        val (deserializedSource, deserializeDuration) = measureTimedValue { CompiledSource.fromBytes(bytes) }
        check(compiledSource == deserializedSource) {
            "Test ${source.name} did not pass compilation check.\nExpected: $compiledSource\nActual: $deserializedSource"
        }
        val (input, output, scope) = TestScope(inputValues)
        val (result, executionDuration) = measureTimedValue { LinVirtualMachine(compiledSource, scope).run() }

        this.result = result.getOrThrow()
        this.input = input
        this.output = output

        this.lexerDuration = lexerDuration
        this.parseDuration = parseDuration
        this.compileDuration = compileDuration
        this.executionDuration = executionDuration
        this.serializeDuration = serializeDuration
        this.deserializeDuration = deserializeDuration

        reportBenchmark()
    }

    private fun reportBenchmark() {
        val title = """
            Benchmark -- ${source.name} $currentPlatform
        """.trimIndent()
        val report = """
            Lexing:     $lexerDuration
            Parsing:    $parseDuration
            Compiling:  $compileDuration
            Executing:  $executionDuration
            Total:      ${parseDuration + compileDuration + executionDuration}
            
            Serializing:    $serializeDuration
            Deserializing:  $deserializeDuration
        """.trimIndent()

        val n = listOf(title, report).flatMap { it.lines() }.maxOf { it.length }

        val separator = "+" + "-".repeat(n + 2) + "+"

        val str = listOf(title, report).joinToString(separator, separator, separator + "\n") { s ->
            "\n" + s.lines().joinToString("\n") { "| " + it.padEnd(n) + " |" } + "\n"
        }

        println(str)
    }
}
