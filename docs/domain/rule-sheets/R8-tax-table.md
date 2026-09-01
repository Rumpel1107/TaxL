# R8 — Art. 241 progressive table and the fiscal adjustment

**Source:** art. 241 E.T. (Ley 2010 de 2019 art. 34) · art. 331 E.T. · art. 577 E.T. ·
art. 70 E.T. · Decretos 128 de 2024, 174 de 2025 and 0449 de 2026

## Values

Rate table — identical for 2023, 2024 and 2025:

| From UVT | To UVT | Marginal rate | Additional UVT |
|---|---|---|---|
| 0 | 1.090 | 0% | 0 |
| 1.090 | 1.700 | 19% | 0 |
| 1.700 | 4.100 | 28% | 116 |
| 4.100 | 8.670 | 33% | 788 |
| 8.670 | 18.970 | 35% | 2.296 |
| 18.970 | 31.000 | 37% | 5.901 |
| 31.000 | — | 39% | 10.352 |

Fiscal adjustment to the cost of fixed assets — art. 70:

| Tax year | Adjustment |
|---|---|
| 2023 | 12,40% |
| 2024 | 10,97% |
| 2025 | 5,81% |

## Formula

    base_uvt      = taxable_income / uvt(tax_year)
    tax_uvt       = (base_uvt − bracket_floor) × marginal_rate + additional_uvt
    tax_pesos     = round_to_nearest_thousand(tax_uvt × uvt(tax_year))

    adjusted_cost = asset_cost × (1 + adjustment(tax_year))

## Conditions

- The table did not change across 2023, 2024 and 2025: Ley 2277 de 2022 did not touch art. 241.
- It applies to the consolidated taxable income of the schedules — art. 331.
- The tax is computed in UVT and converted to pesos only at the end. Converting first and
  applying the rates in pesos is not the same operation.
- The peso result is rounded to the nearest thousand — art. 577.
- A base sitting exactly on a boundary belongs to the bracket below it: the statute's bands
  read *mayor a X hasta Y*, so a base of 1.700 UVT is taxed at 19%, not at 28%. Same reading
  as R15.
- The additional-UVT constants are the statute's own whole-UVT figures and are not recomputed
  from the lower brackets; 116 UVT is the statutory rounding of 115,9.
- The fiscal adjustment applies to fixed assets and occasional gains, not to the ordinary
  labor depuration.
- **Open:** the 2025 fiscal adjustment rests on Decreto 0449 de 2026, whose number and date are
  unconfirmed against the Diario Oficial.

## Test case

**Given** taxable income of $69.718.600, tax year 2025
**When** the table resolves → base 1.400 UVT, the 19% bracket, 58,9 UVT, `tax_pesos` =
$2.933.000

**Given** taxable income of $248.995.000, tax year 2025
**When** the table resolves → base 5.000 UVT, the 33% bracket, (900 × 33%) + 788 = 1.085 UVT,
`tax_pesos` = $54.032.000

**Given** taxable income of exactly $84.658.300, tax year 2025
**When** the table resolves → base 1.700 UVT sits on a boundary and belongs to the 19% bracket,
giving 115,9 UVT and `tax_pesos` = $5.772.000, not the $5.777.000 the upper bracket would give

**Given** a fixed asset with a cost of $100.000.000, tax year 2025
**When** the adjustment applies → `adjusted_cost` = $105.810.000
