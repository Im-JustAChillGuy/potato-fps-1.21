# Potato FPS (recovered source)

This source was **decompiled from the built jar** (`potato-fps-1.0.3`) using CFR,
since the original repo/source wasn't available. It should compile and behave the
same, but a few things to double check before treating it as your canonical repo:

- Variable/field names may differ from your originals (decompilers reconstruct
  logic, not your naming).
- Comments, Javadoc, and any dead/unused code you had are gone.
- No `build.gradle`, `gradle.properties`, or Fabric Loom setup was included in the
  jar, so you'll need to recreate your build files (or copy them from a Fabric
  example mod template + set `minecraft: ~1.21.11`, `fabricloader >=0.18.4`,
  `java >=21` to match `fabric.mod.json`).
- `mixin/ExampleMixin.java` looks like the default Fabric template mixin — you may
  have intended to remove it.

Included as-is from the jar: `fabric.mod.json`, both mixin config JSONs, the mod
icon, and the license file.
