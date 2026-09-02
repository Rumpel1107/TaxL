# R5 — Dependents, both modalities

**Source:** art. 387 inc. 2 E.T. · art. 336 num. 3 inc. 2 E.T. (Ley 2277 de 2022 art. 7) ·
Decreto 2231 de 2023

## Values

| Tax year | 384 UVT — art. 387 | 72 UVT per dependent — art. 336 num. 3 |
|---|---|---|
| 2023 | $16.286.208 | $3.053.664 |
| 2024 | $18.072.960 | $3.388.680 |
| 2025 | $19.122.816 | $3.585.528 |

## Formula

    deduction_387   = if dependants > 0 then min(0.10 × labor_income, 384 × uvt(tax_year)) else 0
    deduction_336_3 = min(dependants, 4) × 72 × uvt(tax_year)

## Conditions

- The two modalities are independent and both apply to the same dependent when the taxpayer
  has income from a labor relationship — Decreto 2231 de 2023.
- The art. 387 deduction does not scale with the number of dependents: one dependent and four
  yield the same amount.
- The 4-dependent limit applies to the art. 336 num. 3 deduction only.
- The art. 387 deduction is inside the general cap (R7); the art. 336 num. 3 deduction is
  outside it and is subtracted after the cap has been applied.
- The art. 387 cap is 32 UVT monthly; the engine resolves it annually at 384 UVT.
- **Open:** who qualifies as a dependent (art. 387 par. 2) is not specified here. The engine
  takes the count as an input it cannot verify.

## Test case

**Given** labor income of $100.000.000 and 4 dependents, tax year 2025
**When** both modalities resolve → `deduction_387` = $10.000.000, under the $19.122.816 cap,
and `deduction_336_3` = 4 × $3.585.528 = $14.342.112

**Given** the same case with 5 dependents
**When** both modalities resolve → neither amount moves: `deduction_387` stays $10.000.000 and
`deduction_336_3` stays $14.342.112, the count capped at 4

**Given** labor income of $250.000.000 and 1 dependent, tax year 2025
**When** both modalities resolve → 10% would be $25.000.000, so the 384 UVT cap governs and
`deduction_387` = $19.122.816; `deduction_336_3` = $3.585.528
