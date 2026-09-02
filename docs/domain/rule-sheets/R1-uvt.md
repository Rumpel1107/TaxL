# R1 — UVT value per tax year

**Source:** art. 868 E.T. · DIAN Res. 001264 de 2022 (TY2023), 000187 de 2023 (TY2024),
000193 de 2024 (TY2025)

## Values

| Tax year | UVT |
|---|---|
| 2023 | $42.412 |
| 2024 | $47.065 |
| 2025 | $49.799 |

## Formula

    threshold_pesos = uvt_count × uvt(tax_year)

## Conditions

- A threshold is stored as its UVT count, never as pesos.
- The applicable UVT is the one of the tax year declared, not the one in force at filing date.
- A threshold resolves to the exact product. Art. 868 rounding does not reach it — the UVT is a
  unit of measure, and rounding to the nearest thousand applies to the peso figures computed
  from it, where art. 577 governs (R8).
- The TY2025 filing threshold of 1.400 UVT is therefore $69.718.600, the figure the engine
  carries; $69.719.000 is also published.

## Test case

**Given** a cap of 790 UVT (R4)
**When** resolved for tax year 2025 → $39.341.210
**When** only the tax year changes to 2024 → $37.181.350, no other parameter edited
