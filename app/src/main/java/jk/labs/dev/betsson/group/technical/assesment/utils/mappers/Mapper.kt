package jk.labs.dev.betsson.group.technical.assesment.utils.mappers

abstract class Mapper<in I, out O> {
    abstract fun map(entry: I): O
}