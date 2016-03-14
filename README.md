# Chromatophore

<p align="center">
  <img alt="Chromatophore" 
       src="http://api.ning.com/files/DtcI2O2Ry7AJoOIMBphmUFeoaMEubY7gFqlqyrFCXFeQNEndPfCUdRfHTWstQwc1lExFVz0ViYoYlMh6h1gYW7lHW3BR5isb/1082130492.png"
       width="250" />
</p>


Chromatophore is a collection of reusable reagent components.

The goals of this effort are two-fold:

  - Components should have verified functionality with advanced compilation
  - There should be out-of-the-box support for server side rendering via Dmitri Sotnikov's [Serverside Reagent Rendering](http://yogthos.net/posts/2015-11-24-Serverside-Reagent.html)

## Usage

### [Devcards](https://github.com/bhauman/devcards)
Devcards is primarily helpful in component development.

To run CuttleFi.sh using devcards, type at the terminal prompt:

```
# lein devcards
```

...and then visit [http://localhost:3449/cards.html](http://localhost:3449/cards.html)

### Testing

#### Continuous Testing

  - ClojureScript

    To continuously test ClojureScript, type at the terminal prompt:

    ```
    # lein auto-test
    ```
    
  - Clojure
    
    To continuously test Clojure, type at the terminal prompt:
    
    ```
    # lein test-refresh
    ```

#### Test Advanced Compilation

ClojureScript support advanced compilation including optimization; so a seperate test command is provided for this.

```
# lein advanced-test
```

### Cleaning

The usual way to clean this repository is to type at the terminal prompt:

```
# lein clean
```

However, this does not get rid of the `node_modules` directory (since it requires a large download to rebuild), nor the `figwheel_server.log` file.

If you want to *completely* clean the above files, run:

```
# lein deep-clean
```

## License

© 2015. Cuttlefi.sh LLC, All rights reserved