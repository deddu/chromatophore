(ns chromatophore.mdl
  (:require [cljsjs.react.dom]
            [chromatophore.utils
             :refer [ratom? boolean?]
             :refer-macros [defnp fnp]]
            [reagent.core :as reagent]
            [reagent.ratom
             :refer [atom]
             :refer-macros [run!]]))

(defn toclasses [lst] 
  (clojure.string/join " " (filter #(not(nil? %)) lst)))
  
  
(defn btn "mdl button wrapper"
  [{:keys [ripple? raised? colored? primary? accent? fab? text]
    :or   {ripple? false raised? false colored? false primary? false accent? false}}]
  (fn []
    (let [ classes (toclasses
                     (list 
                      (if ripple? "mdl-js-ripple-effect")
                      (if raised? "mdl-button--raised")
                      (if colored? "mdl-button--colored")
                      (if primary? "mdl-button--primary")
                      (if accent? "mdl-button--accent")
                      (if fab? "mdl-button--fab")))]
        [:button.mdl-button.mdl-js-button 
          {:class classes};} 
          text])))