package com.sksamuel.hoplite.decoder

import arrow.data.invalidNel
import arrow.data.validNel
import com.sksamuel.hoplite.ConfigFailure
import com.sksamuel.hoplite.ConfigResult
import com.sksamuel.hoplite.Node
import com.sksamuel.hoplite.StringNode
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.reflect.KType

class FileDecoder : NonNullableDecoder<File> {
  override fun supports(type: KType): Boolean = type.classifier == File::class
  override fun safeDecode(node: Node,
                          type: KType,
                          registry: DecoderRegistry,
                          path: String): ConfigResult<File> = when (node) {
    is StringNode -> File(node.value).validNel()
    else -> ConfigFailure.TypeConversionFailure(node, path, type).invalidNel()
  }
}

class PathDecoder : NonNullableDecoder<Path> {
  override fun supports(type: KType): Boolean = type.classifier == Path::class
  override fun safeDecode(node: Node,
                          type: KType,
                          registry: DecoderRegistry,
                          path: String): ConfigResult<Path> = when (node) {
    is StringNode -> Paths.get(node.value).validNel()
    else -> ConfigFailure.TypeConversionFailure(node, path, type).invalidNel()
  }
}