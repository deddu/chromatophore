(ns chromatophore.markdown
  (:require [chromatophore.utils #?@(:cljs [:refer-macros [defnp]]
                                     :clj  [:refer [defnp]])]
            [markdown.core #?@(:cljs [:refer [md->html]]
                               :clj  [:refer [md-to-html-string]])]
            [schema.core :as schema]))

(defnp
  md
  "A component for rendering markdown"
  [props,
   text :- schema/Str]
  [:div.markdown
   (assoc props
     :dangerouslySetInnerHTML
     {:__html (#?(:cljs md->html
                  :clj  md-to-html-string) text)})])