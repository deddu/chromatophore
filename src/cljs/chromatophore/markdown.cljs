(ns chromatophore.markdown
  (:require [chromatophore.utils :refer-macros [defnp]]
            [markdown.core :refer [md->html]]
            [schema.core :as schema]))

(defnp md
  "A component for rendering markdown"
  [props text :- schema/Str]
  [:div.markdown
   (assoc props :dangerouslySetInnerHTML {:__html (md->html text)})])
