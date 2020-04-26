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
    wget https://github.com/baato/osmosis-remove-author/releases/download/v1.0.0/osmosis-remove-author-1.0.0.tar.gz && \
    mkdir osmosis-remove-author && \
    tar xvzf osmosis-remove-author-1.0.0.tar.gz -C ./osmosis-remove-author && \
    cd osmosis-remove-author && \
    zip -r ../osmosis-remove-author.zip * && \
    cd .. &&  rm -rf osmosis-remove-author *.tar.gz 

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

