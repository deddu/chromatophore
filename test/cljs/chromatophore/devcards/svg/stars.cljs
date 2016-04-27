(ns chromatophore.devcards.svg.stars
  (:require [chromatophore.svg.stars :refer [five-star-rating]]
            [cljs.test :refer-macros [testing is]]
            [devcards.core :refer-macros [defcard-rg]]
            [clojure.string :refer [split-lines trim join]]
            [reagent.core :as reagent]))

(defcard-rg Default-2.5-Stars
  [five-star-rating {:score 2.5}])

(defcard-rg Red-3-Stars-Error-2.5
  [five-star-rating {:score 3 :error 2.5 :color "red"}])

(defcard-rg Blue-2.5-Stars-Error-1
  [five-star-rating {:score 2.5 :error 1 :color "blue"}])

(defcard-rg Green-3.2-Stars-Error-2
  [five-star-rating {:score 3.2 :error 2 :color "green"}])

(defcard-rg Orange-3-Stars-Error-4
  [five-star-rating {:score 3 :error 4 :color "orange"}])

(defcard-rg Orange-3-Stars-Error-4-Red-Stroke
  [five-star-rating {:score 3 :error 4 :color "orange" :stroke "red"}])

(defcard-rg Green-4.7-Stars-Error-2-Blue-Stroke
  [five-star-rating {:score 4.7 :error 2 :fill "green" :stroke "blue"}])
