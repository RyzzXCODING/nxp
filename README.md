# No XP Cost

Mod Fabric untuk Minecraft **1.21.11** yang menghilangkan biaya XP (experience level)
saat memakai **anvil** dan **enchanting table**. Perbaiki, gabungkan, ganti nama item
di anvil, atau pilih enchant di meja enchant — level XP kamu tidak akan berkurang
sama sekali. Lapis lazuli di enchanting table tetap terpakai seperti biasa.

Dibuat oleh **Alpoyy**.

## Cara kerjanya

Mod ini pakai **Mixin** (lewat Fabric Loader), bukan ubah file Minecraft asli:

- `AnvilScreenHandlerMixin` → mencegat method `onTakeOutput` di anvil, membatalkan
  pemanggilan `addExperienceLevels()` yang biasanya mengurangi XP saat kamu
  mengambil hasil dari anvil.
- `EnchantmentScreenHandlerMixin` → mencegat method `onButtonClick` di enchanting
  table, membatalkan pemanggilan `addExperienceLevels()` yang biasanya mengurangi
  XP saat kamu memilih salah satu opsi enchant.

Redirect-nya di-scope hanya ke dua method itu, jadi XP yang kamu dapat dari hal lain
(membunuh mob, menambang, dll) tidak ikut terpengaruh.

## Build

Butuh **Java 21** dan koneksi internet (Gradle akan download Minecraft, Yarn
mappings, Fabric Loader, dan Fabric API saat build pertama).

```bash
# Linux / macOS
./gradlew build

# Windows
gradlew.bat build
```

> Project ini belum menyertakan `gradlew` (Gradle Wrapper binary) karena dibuat
> tanpa akses internet. Sebelum build pertama, jalankan sekali di komputer yang
> ada Gradle terinstall dan terhubung internet:
> ```bash
> gradle wrapper --gradle-version 8.11
> ```
> Setelah itu `gradlew` / `gradlew.bat` akan muncul dan bisa dipakai seperti biasa.
> Alternatifnya, buka folder ini langsung di IntelliJ IDEA dengan plugin
> **Fabric** / **Minecraft Development**, lalu biarkan Gradle sync — IDE akan
> otomatis urus semuanya.

Hasil `.jar` ada di `build/libs/noxpcost-1.0.0.jar` — tinggal taruh di folder
`mods` instance Fabric kamu (butuh **Fabric Loader** + **Fabric API** versi
1.21.11 terpasang juga).

## Versi yang dipakai

| Komponen       | Versi              |
|----------------|---------------------|
| Minecraft      | 1.21.11             |
| Fabric Loader  | 0.18.1+             |
| Fabric API     | 0.140.0+1.21.11     |
| Yarn mappings  | 1.21.11+build.4     |
| Java           | 21                   |

Kalau Gradle bilang versi Yarn / Fabric API di atas sudah tidak ada (kadang
build terbaru berubah), cek versi paling baru di:
- https://fabricmc.net/develop/
- https://modrinth.com/mod/fabric-api/versions

lalu update angkanya di `gradle.properties`.

## Struktur project

```
noxpcost/
├─ build.gradle
├─ settings.gradle
├─ gradle.properties
├─ LICENSE
└─ src/main/
   ├─ java/dev/zet/noxpcost/
   │  ├─ NoXpCostMod.java                  (entrypoint mod)
   │  └─ mixin/
   │     ├─ AnvilScreenHandlerMixin.java
   │     └─ EnchantmentScreenHandlerMixin.java
   └─ resources/
      ├─ fabric.mod.json
      ├─ noxpcost.mixins.json
      └─ assets/noxpcost/icon.png


## Cloud Build (GitHub Actions)

This source includes `.github/workflows/build.yml`. Push the project to a GitHub repository, then open **Actions → Build No XP Cost → Run workflow**. When the workflow finishes successfully, download the `noxpcost-minecraft-1.21.11` artifact containing the built JAR(s). The workflow uses Java 21 and Gradle 9.2.1.
