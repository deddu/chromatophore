(ns chromatophore.devcards.mdl
  (:require   [chromatophore.mdl :refer [btn]]
              [devcards.core :refer-macros [deftest defcard defcard-rg]]
              [reagent.core :as reagent]
              [reagent.ratom :refer-macros [reaction]]))


(defcard-rg button "takes the props like a good boy" 
  [:div
    [btn {:text "default"}] 
    [btn {:text "raised" :raised? true}]
    [btn {:text "fab" :fab? true}]
    [btn {:text "primary"  :primary? true}]
    [btn {:text "colored"  :colored? true}]
    [btn {:text "accent"  :accent? true}]
    [btn {:text "ripple"  :ripple? true}]])

(defcard-rg button-multi "takes multiple props like a good boy" 
  [:div
    [btn {:text "raised primary" :raised? true :primary? true}]
    [btn {:text "fab accent" :fab? true :accent? true}]
    [btn {:text "raised colored ripple" :raised? true :colored? true :primary? true}]])
    
