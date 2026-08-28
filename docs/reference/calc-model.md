# Calculation model

> **What goes in this document:** the shape of the deterministic engine, as input for the future
> `003-calc-engine` spec. What the code must implement, not how any prototype was built.
>
> No taxpayer figures (D31). Rule numbers refer to `docs/domain/rules.md`.

## Four parts

- **Parameters, versioned by tax year.** UVT, caps, rates and brackets. Caps are held as UVT
  multiples and resolved against the year's UVT, never as pesos. Switching tax year touches
  nothing else — that is metric M3.
- **Inputs.** Identification, income, INCRNGO, deductions, exempt income, out-of-cap items, net
  worth, thresholds, payments. Every input carries three things, not one: the value, the document
  that supports it, and the Form 210 line it lands on. The second and third are what make M4
  (traceability) possible.
- **Depuration pipeline.** Five ordered steps, below.
- **Form 220 → 210 mapping.** Every line of the withholding certificate mapped to its treatment,
  including the lines that mean the case is out of scope and must fail loudly.

## Depuration pipeline

1. Net income — income less INCRNGO.
2. The base for line 91.
3. The 40% cap, as the lower of two limits: 40% of the base and 1.340 UVT (R7). Items subject to
   the cap and items outside it are separated before this step, and the amount lost to the cap is
   kept as an output in its own right — it is the product's headline insight (D19).
4. Taxable income.
5. Tax, by lookup against the art. 241 progressive table (R8).

Alongside the pipeline, three modules that do not feed it: the filing-obligation check against the
four thresholds (R2), the net-worth comparison, and occasional gains at 15%.

## Conventions

- Intermediate values stay exact; only the final tax is rounded to thousands (art. 577).
- Caps that can bind must be tested at their binding point — an individual cap and the global cap
  can each govern in different years for the same taxpayer.
