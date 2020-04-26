# Osmosis `bump-ids` plugin 

When repopulating a private OpenStreetMap API DB, it is useful to bump IDs, as this helps prevents conflicts.

The plugin works out-of-the-box to bump ids by a specified number

## Installation

You can choose to manually clone the repo, build the Java classes usin maven, and create a ZIP file that includes:
 
 - all the contents inside `./target/classes/` folder.
 - the file `plugin.xml`

After that you can move the resulting ZIP file to the a folder called `plugins` inside your working directory.

## Downloading from releases 

Alternatively, you can also download an existing release, and use the following steps for getting started. This is more useful in containerized environments.

```
mkdir -p ~/.openstreetmap/osmosis/plugins && \
    cd ~/.openstreetmap/osmosis/plugins && \
    wget https://github.com/baato/osmosis-bump-ids/releases/download/v1.0.0/osmosis-bump-ids-1.0.0.tar.gz && \
    mkdir osmosis-bump-ids && \
    tar xvzf osmosis-bump-ids-1.0.0.tar.gz -C ./osmosis-bump-ids && \
    cd osmosis-bump-ids && \
    zip -r ../osmosis-bump-ids.zip * && \
    cd .. &&  rm -rf osmosis-bump-ids *.tar.gz 

```



## Usage
```
osmosis \
    --read-pbf input.osm.pbf \
    --bump-ids by=1000000 \
    --write-pbf output.osm.pbf
```


## Relevant links 
* [OpenStreetMap](http://www.openstreetmap.org/)
* [Osmosis](http://wiki.openstreetmap.org/wiki/Osmosis)

