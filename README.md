This is a script that spaces out groups of objects starting at the same time in your Project Arrhythmia level. Despawn time is spaced out too.

I might include other useful mass editing scripts in this project later, as well as make a simple UI.

# How to use

- download and install Java
- download `assembly/pa-object-spacer.jar` file
- open command line

```
cd C:\path\to\directory\of\jar\file
java -jar pa-object-spacer.jar "C:\path\to\level\directory"
```

this will create a `level2.lsb` file in your level directory

to apply new level file, rename `level.lsb` to something else and rename `level2.lsb` to `level.lsb`

**WARNING** The script only works with levels made on legacy/dev branch. Levels made in older versions of the editor need to be saved in legacy branch first.

**IMPORTANT** It's not recommended to use this script only before level upload. It might be inconvenient to work with groups of objects all starting at a different point in time, and you won't be able to use BPM snap for them
