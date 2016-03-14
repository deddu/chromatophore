(ns chromatophore.devcards.toggle
  (:require [chromatophore.markdown :refer [md]]
            [chromatophore.toggle :refer [unfold-reveal toggle-tree]]
            [devcards.core :refer-macros [deftest defcard defcard-rg]]
            [reagent.core :as reagent]
            [reagent.ratom :refer-macros [reaction]]))


(defcard-rg unfold-reveal
  "`unfold-reveal` is a component for displaying hierarchical content.
       You should be able to click the triangles to toggle reveal"
  [unfold-reveal {:unfolded? true}
   "foo"
   [unfold-reveal "bar" "baz"]
   "borg"
   [unfold-reveal "blag" "blig" "bloo"]])

(defcard-rg unfold-reveal-empty
  "`unfold-reveal` should just display its main argument if there's no children"
  [unfold-reveal "bar"])

(defn unfold-reveal-check []
  (let [status (reagent/atom {:hidden true})]
    (fn []
      [:div
       [:div (str "hidden: " (:hidden @status))]
       [:hr]
       [unfold-reveal
        {:on-toggle #(swap! status assoc :hidden (not %2))}
        ":on-click test"
        ":on-click child-1"
        ":on-click child-3"]])))

(defcard-rg unfold-reveal-on-click
  "`unfold-reveal` should expose an `:on-click` keyword.

     Toggling should change the value of `hidden` specified below."
  [unfold-reveal-check])


(defn unfold-reveal-override-check
  []
  (let [override (reagent/atom nil)]
    (fn []
      [:div
       [:button {:on-click #(do (reset! override nil)
                                (reset! override true))}
        "Reveal All"]
       [:span {:style {:display "inline-block" :width "10pt"}}]
       [:button {:on-click #(do (reset! override nil)
                                (reset! override false))}
        "Collapse All"]

       [:span {:style {:display "block" :height "10pt"}}]
       [unfold-reveal {:unfolded? true
                       :override override}
        "foo"
        [unfold-reveal {:override override} "bar" "baz"]
        "borg"
        [unfold-reveal {:override override} "blag" "blig" "bloo"]]])))

(defcard-rg unfold-reveal-override
  "`unfold-reveal` should accept an `:override` keyword, which specifies ratom

      If that ratom changes to `true`, it should unfold the component.
      If that ratom changes to `false`, it should fold the component.

     Any other value should not do anything.

     In the example below, the two buttons override the toggle of the `unfold-reveal` components.

     Pushing `Reveal All` should force all of the components to unfold.

     Pushing `Collapse All` should force all of the components to fold."
  [unfold-reveal-override-check])

(defcard-rg toggle-tree-test
  "`toggle-tree` is a component that takes a nested hash-map with `:main` and `:children` keys and returns a hiccup tree of `unfold-reveal` components.

    `toggle-tree` recursively constructs components; the base case of the recursion is where its argument is a string or vector."
  [:ul
   [:li
    [:div "Argument is a string"]
    [toggle-tree "This is a string argument"]]
   [:li
    [:div "Argument is a vector"]
    [toggle-tree [:em "This is a vector argument (hiccup code)"]]]])

(def toggle-tree-test-data
  {:main "foo"
   :unfolded? true
   :children [{:main "bar"}
              {:main "baz"
               :children ["foo jr" "bar jr"]}
              {:main "blarg"
               :children [[:div "I'll get you inspector gadget!"]
                          [:em "I'm emphasized"]
                          {:main [:b "Barristan the Bold"]
                           :children ["If you read the books you know he didn't really have children"
                                      [:div "He was just " [:em "awesome"]]]}]}
              "Another entry, just for fun"
              "Still another one"]})

(defcard toggle-tree-data
  "This is the data we will use for testing `toggle-tree-data`'s recursion."
  toggle-tree-test-data)

(defcard-rg toggle-tree-test
  "`toggle-tree` is a component that takes a nested hash-map with `:main` and `:children` keys and returns a hiccup tree of `unfold-reveal` components.

    Note that because we specified \"foo\" to be unfolded in the data structure, it is unfolded."
  [toggle-tree toggle-tree-test-data])

(defcard-rg toggle-tree-params
  "`toggle-tree` also takes an optional first parameter, which is then used as a list of parameters for all subsequent children in the tree.

     In this example, we set `{:unfolded? true}` so the tree is completely unfolded.

    Note that parameters set by nodes in the tree override these global parameters.

    `shift-click` expands/collapses all children."
  [toggle-tree {:unfolded? true} toggle-tree-test-data])
