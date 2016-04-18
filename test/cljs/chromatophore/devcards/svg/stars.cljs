(ns chromatophore.devcards.svg.stars
  (:require [chromatophore.svg.stars :refer [five-star-rating]]
            [cljs.test :refer-macros [testing is]]
            [devcards.core :refer-macros [defcard-rg]]
            [clojure.string :refer [split-lines trim join]]
            [reagent.core :as reagent]))

(defcard-rg Default-2.5-Stars
  [five-star-rating {:score 2.5}])

(defcard-rg Red-3-Stars-Error-2.5
  [five-star-rating {:score 3 :error 2.5 :fill "red" :stroke "red"}])

(defcard-rg Blue-2.5-Stars-Error-1
  [five-star-rating {:score 2.5 :error 1 :fill "blue" :stroke "blue"}])

(defcard-rg Green-4-Stars-Error-2
  [five-star-rating {:score 4 :error 2 :fill "green" :stroke "green"}])

(defcard-rg Orange-3-Stars-Error-4
  [five-star-rating {:score 3 :error 4 :fill "orange" :stroke "orange"}])
