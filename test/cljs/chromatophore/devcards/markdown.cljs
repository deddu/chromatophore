(ns chromatophore.devcards.markdown
  (:require [chromatophore.markdown :refer [md]]
            [devcards.core :refer-macros [defcard-rg]]
            [reagent.core]))

(defcard-rg markdown
  "
 Below is a little demonstration of the `[md ...]` component we will use based on `yogthos/markdown-clj`.
 "
  [md
   "
<hr/>
 This is some test text, should have *cool* formatting.

 Here's a list:


 Here's some (inline) code `blah`. This is a block of code:

 ```javascript
 console.log('Best programming language EVAR');
 ```

Here's a quote:
> \"Simple made easy\" ~ From the dude who invented the `clojure.lang.IMap` interface
"])
