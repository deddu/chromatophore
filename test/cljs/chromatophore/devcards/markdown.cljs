(ns chromatophore.devcards.markdown
  (:require [chromatophore.markdown :refer [md]]
            [cljs.test :refer-macros [testing is]]
            [devcards.core :refer-macros [defcard-rg deftest]]
            [clojure.string :refer [split-lines trim join]]
            [reagent.core :as reagent]))

(def is-client?
  (not (nil? (try (.-document js/window)
                  (catch js/Object e nil)))))

(defn add-test-node [node-name]
  (let [doc js/document
        body (.-body js/document)
        node (.createElement doc "div")]
    (.setAttribute node "id" node-name)
    (.appendChild body node)
    node))

(defn with-mounted-component [component call-back]
  (when is-client?
    (let [node-id (str (random-uuid))
          node (add-test-node node-id)]
      (reagent/render-component component node #(call-back component node))
      (reagent/unmount-component-at-node node)
      (reagent/flush)
      (.removeChild (.-body js/document) node))))


(defn found-in [regular-expression node]
  (let [node-html (.-innerHTML node)]
    (if (re-find regular-expression node-html)
      true
      (do (println "Pattern " (str regular-expression)
                   " not found in HTML: " node-html)
          false))))

(defcard-rg markdown
            "
           Below is a little demonstration of the `[md ...]` component we will use based on `yogthos/markdown-clj`.
           "
            [md
             (->>
               "<hr/>
               This is some test text, should have *cool* formatting.

               Here's a list:

               - 1
               - 2
               - 3

               Here's some (inline) code `blah`. This is a block of code:
               ```javascript
               console.log('Best programming language EVAR');
               ```

               Here's a quote:
               > \"Simple made easy\"<br/>
               > ~ *From the dude who invented the `clojure.lang.IMap` interface*
               "
               split-lines
               (map trim)
               (join "\n"))])

(deftest
  markdown-test
  (testing "Checking that `[md \"testing 1, 2, 3\"]` (bound to `test-markdown-node1`) component contains \"testing 1, 2, 3\""
    (with-mounted-component
      [md "testing 1, 2, 3"]
      (fn [_ test-markdown-node1]
        (is (found-in #"testing 1, 2, 3" test-markdown-node1)))))

  (testing "Checking that `[md \"*BOOM*\"]` (bound to `test-markdown-node2`) component contains \"<em>BOOM</em>\""
    (with-mounted-component
      [md "*BOOM*"]
      (fn [_ test-markdown-node2]
        (is (found-in #"<em>BOOM</em>" test-markdown-node2))))))