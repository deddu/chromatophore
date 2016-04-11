(ns chromatophore.doo.runner
  (:require [chromatophore.utils-test]
            [chromatophore.devcards.markdown]
            [doo.runner :refer-macros [doo-tests]]))

(doo-tests 'chromatophore.utils-test 'chromatophore.devcards.markdown)
