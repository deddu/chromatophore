(ns chromatophore.devcards.editable-title
  (:require [cljsjs.react.dom.server]
            [chromatophore.editable-title
             :refer [click-to-edit
                     click-icon-to-edit]]
            [devcards.core :refer-macros [defcard-rg]]
            [reagent.core :as reagent]))

(defcard-rg basic-click-to-edit
  "The following is a `div` component that when clicked becomes editable and will run `:on-save` when the user is done filling in the value

   Notes:

      - Hitting `return`, `tab`, or clicking elsewhere should save the text
      - Hitting `escape` should revert the text
      - If you enter HTML input like `<hr>`, it should be automatically escaped
      - If `:on-save` is specified, then the argument passed to the component is displayed when *not editing* (it is presumably from an atom).  Otherwise the component displays its own internal state
      - If the text is deleted, the element should refuse to save
      - All parameters (such as `style`) should be passed to the underlying `div`
      - If the argument is an `atom`, it should be dereferenced."

  [click-to-edit
   "click to edit me!"])

(defcard-rg double-click-to-edit
  "For this click to edit component, you must *double click*"

  [click-to-edit
   {:click-type :double}
   "double click to edit me!"])


(defn two-click-to-edit-divs
  [txt]
  (let [title (reagent/atom txt)]
    (fn []
      [:div
       [click-to-edit {:on-save #(reset! title %)} @title]
       [click-to-edit {:on-save #(reset! title %)} @title]
       [:hr]
       [:div "The following *can not* be edited, however it should have the same value as the other two divs"]
       [:div @title]])))

(defcard-rg two-click-to-edit-divs-sharing-state
  "Here are two editable title objects which are sharing a value.

   Editing one should edit the other."
  [two-click-to-edit-divs "This text is shared between two components"])

(defcard-rg click-icon-to-edit-check
  "This `click-to-edit` component is controlled by an icon.

Notes:

  - If the icon is clicked, its colors should be inverted and the cursor should be moved to the end of the text area.
  - If the text is clicked, the text should be highlighted.
  - The icon should not be selectable."
  [click-icon-to-edit "click my icon to edit me (or just click me)"])


(defn two-click-icon-to-edit-divs
  [txt]
  (let [title (reagent/atom txt)]
    (fn []
      [:div
       [click-icon-to-edit {:on-save #(reset! title %)} @title]
       [click-icon-to-edit {:on-save #(reset! title %)} @title]
       [:hr]
       [:div "The following *can not* be edited, however it should have the same value as the other two divs"]
       [:div @title]])))

(defcard-rg two-click-icon-to-edit-check
  "These `click-icon-to-edit` components should share the same value."
  [two-click-icon-to-edit-divs "click my icon to edit me (or just click me)"])
