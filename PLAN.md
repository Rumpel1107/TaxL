# Project Plan — Income Tax Return Simulator (Individual Taxpayers · Colombia)

> **How to read this document**
> Organized in two layers:
> - 🟦 **[PROJECT]** → content specific to the tax simulator.
> - 🟩 **[TEMPLATE]** → reusable methodology structure. For a new project, copy the sections marked this way and fill them in.
>
> Version: **v0.3 (merge)** · Status: Initial decisions closed · Owner: Rumpel
>
> **v0.2 → v0.3 changes (2026-07-06):** merged with the business-model plan drafted in session
> with Claude. Added: §3 monetization funnel; exógena ingestion moved **P3 → P2** (it is now on
> the product MVP critical path); §7 Domain Rule Registry (R1–R14, verification fichas);
> owner tags `[R]/[A]/[R+A]` in methodology; WTP validation story; new risks; decision log updated.
> This file supersedes both the previous v0.2 and the session draft `PLAN_liquidador_renta.md`.

---

## 1. 🟦 Objective

**General objective.** Build a simulator that allows an individual taxpayer in Colombia to autonomously obtain a **reliable draft** of their income tax return, with field-by-field explanations and recommendations, positioned as an **educational / pre-check tool** (not as official tax-filing software) — monetized through a free-to-paid funnel (§3).

**MVP objective (Phase 0).** Validate a **deterministic calculation engine in Excel**, for the **employee (asalariado)** profile, tax year **2025**, confirming it reproduces, to the peso, already-filed tax returns.

**Why this scope.** The highest-risk piece (the tax logic) is tackled first, with the cheapest medium (Excel) and free ground truth (own past returns). UI, document ingestion, and AI come later — on top of an engine already known to be correct.

---

## 2. 🟦 Product North Star and Value Proposition

> **North Star: "Understand and verify before filing."**

The DIAN already offers the **"declaración sugerida"**: a **pre-filled** form based on what third parties reported, which the user reviews, edits, or accepts — but **any inconsistency remains the taxpayer's responsibility**, and it's only available to some profiles.

The simulator **does not compete on "filling out the form"** (the DIAN already does that). It competes in the layer **before that**: making the person **understand and trust** the numbers before accepting anything official.

**Differentiators vs. the suggested return**

| # | The suggested return... | The simulator... |
|---|--------------------------|-------------------|
| 1 | Pre-fills, doesn't teach | **Explains** every field (tooltips) |
| 2 | Reflects only what third parties reported | **Reconciles** third-party data (exógena) vs. your actual documents and flags third-party errors |
| 3 | Doesn't pick the best option for you | **Optimizes**: compares scenarios (real costs / 25% exempt income / presumptive costs) |
| 4 | Doesn't warn about risk | **Flags risk**: wealth/equity comparison, the "moving money between your own accounts" trap |
| 5 | Is the official act (with legal liability) | Is a **safe pre-check**, a draft before touching anything official |

> In one sentence: the suggested return gives you a form to **accept**; the simulator gives you the understanding to **trust** it.

**North Star Metric (candidate, once there are real users).** % of users who complete a draft and report feeling able to **verify** their own return. In the MVP (Excel only, own data) the north star translates into an operational proxy: **every number in the Output is explainable and traceable** to an input or a parameter.

---

## 3. 🟦 Monetization Funnel *(new in v0.3)*

**Design insight (session 2026-07-06):** nobody enters their data with discipline. In the product
(P2+), data enters **by file, not by form**: the user downloads their exógena report from the DIAN
portal (guided by a curated YouTube tutorial — curate an existing one, don't produce) and uploads it.
Manual entry remains available as fallback and is the P0-Excel mechanism.

| Tier | Deliverable | Price |
|---|---|---|
| **Free (touchpoint)** | Conservative tax estimate WITHOUT deductions (only what's inferable from the file) + filing deadline by ID digits + applicable rules/thresholds. Framed explicitly as **"conservative estimate / maximum scenario"** — legitimate anchoring, never alarmism. | $0 |
| **X (core)** | Full liquidation + deduction validation with legal caps + step-by-step normative breakdown + supporting-documents checklist. | One-time per season (hypothesis: 10–20% of an accountant's fee; validate with M5) |
| **Y (upsell, post-MVP)** | Recommendations to reduce next year's tax (AFC, voluntary pension, etc.). ⚠️ Closest to "tax advisory" — gated on the legal-boundary research task. | Additional payment |

Funnel mechanics: the free no-deductions estimate is deliberately the **ceiling**; X literally sells
"see how much you can lower it". **The deduction IS the product.** Form 210 draft output stays in a
later phase (high value, high error consequence); communicated in X as "coming soon".

---

## 4. 🟦 Success Metrics and "Validated" Criteria

| # | Metric | MVP Target |
|---|--------|------------|
| M1 | Match with real tax returns | **$0** difference on key lines of Form 210, for my last **3** filed returns |
| M2 | Test case coverage | 100% of the worked examples from the reference webinar (employee and self-employed) reproduced correctly |
| M3 | Parameterization | The engine switches tax year by editing **only** the Parameters sheet |
| M4 | Traceability | Every Output value can be traced back to an Input cell or a parameter |
| M5 | Willingness-to-pay signal *(new)* | Show the validated Excel result to ≥3 people in the target profile; record: would pay yes/no + how much |

> **MVP closing rule:** the MVP is not "done" until M1 and M2 are met. M5 gates the decision to
> invest in P2 (product), not the closing of P0.

---

## 5. 🟦 Scope

**In scope for MVP (Phase 0)**
- **Employee (asalariado)** profile (with or without freelance/honorarios income opting for the 25% exempt income benefit).
- General schedule (cédula general) → labor income sub-schedule.
- Filing-obligation determination via the 4 thresholds.
- Tax liability calculation (Art. 241 table).
- Wealth/equity comparison (comparación patrimonial).
- Output mapped to Form 210 lines.
- **Manual** data entry *(P0 only — the product entry point is the exógena file, see §3 and P2)*.

**Out of scope for MVP (later phases)**
- Self-employed with real costs/expenses and presumptive costs (Resolution 532); capital income; non-labor income; dividends; complex occasional gains (inheritances).
- OCR of individual certificates *(exógena parsing is NOT out of scope anymore — it moved to P2)*.
- Login, persistence, AI-driven recommendations (tier Y).
- Wealth tax and advance tax payment — modeled but not prioritized in v0.
- Third-party personal data handling (Law 1581): applies from Phase 2 onward.

---

## 6. 🟦 Phases (Epics)

| Phase | Epic | Expected Outcome | Main Risk it Reduces |
|-------|------|-------------------|------------------------|
| **P0** | Calculation engine in Excel (employee, TY2025) | Excel that matches real filed returns + WTP signal (M5) | Incorrect tax calculation; building without demand |
| **P1** | Professional validation + coverage expansion | Engine reviewed by an accountant; new profiles added | Regulatory errors; scope |
| **P2** | Software prototype **+ exógena ingestion** *(moved from P3)* | App with the funnel's free tier live: exógena upload → parse → depurate → conservative estimate + deadline + rules. Paid tier X on top. Login, disclaimer, persistence. Manual entry as fallback | Personal data (Law 1581); **exógena depuration (R11–R14) — now the #1 technical unknown** |
| **P3** | Certificate ingestion (OCR) + explanation layer | OCR of individual certificates; tooltips, alerts | Heterogeneous OCR |
| **P4** | Recommendation layer (AI) — tier Y | Optimization scenarios, next-year recommendations | Trusting the AI with the calculation; legal boundary |

**Cross-cutting principles (apply to all phases):**
1. **The engine is deterministic.** AI never calculates the tax; it only extracts documents, explains, and recommends.
2. **Everything is parameterized by tax year.** Parameter *values* are data, never hardcoded — and every value requires its verification ficha (§7) before being trusted, regardless of source.
3. **The "exógena" report is the gold-standard input** (Excel file, includes the 4-threshold summary) — and, since v0.3, the **product's entry point**, not a later enhancement.
4. **Educational / pre-check positioning**, always with a disclaimer and a recommendation to seek professional validation.

---

## 7. 🟦 Domain Rule Registry *(new in v0.3 — the defensible asset)*

Vibe-coding boundary applies 100%: **every rule below must be personally understood, documented,
and tested by Rumpel** against primary sources (Estatuto Tributario, annual decrees, DIAN
resolutions). No rule is accepted "because an LLM said so" — including the parameter values already
written into US-P0-001's ACs (webinar-sourced; written ≠ verified).
**Closed rule = ficha:** rule in own words + numeric example + test case in the validation suite.

**Calculation rules:**

| # | Rule | Primary source | Status |
|---|---|---|---|
| R1 | UVT value per tax year | Annual DIAN resolution | ⬜ |
| R2 | Filing-obligation thresholds (4, in UVT) | Annual deadlines decree | ⬜ |
| R3 | Non-taxable income / INCRNGO (mandatory health & pension contributions) | E.T. arts. 55-56 | ⬜ |
| R4 | 25% exempt labor income + cap | E.T. art. 206 + current reform | ⬜ |
| R5 | Dependents deduction (both modalities post-Ley 2277) + caps | E.T. art. 336 + related | ⬜ |
| R6 | Individual caps: prepaid health, mortgage interest, AFC/voluntary pension | E.T. various arts. | ⬜ |
| R7 | Global 40% cap / UVT ceiling on exemptions + deductions; capped vs uncapped items | E.T. art. 336 | ⬜ |
| R8 | Art. 241 progressive table (UVT brackets + per-bracket formula) + fiscal adjustment | E.T. art. 241 | ⬜ |
| R9 | Withholdings/advance payment; balance due vs refund | E.T. + Form 210 | ⬜ |
| R10 | Filing calendar by ID digits (defines the seasonal peak) | Annual deadlines decree | ⬜ |

**Exógena depuration rules (the #1 technical unknown since the P3→P2 move):**

| # | Rule | Note | Status |
|---|---|---|---|
| R11 | Structure/format of the citizen-downloadable exógena file (sheets, concepts, versions) | Discovered with real files | ⬜ |
| R12 | Mapping: exógena concept → income type / schedule | Core of the parser | ⬜ |
| R13 | Exclusions & false income: loan disbursements, refunds, transfers between own accounts, gross third-party amounts that aren't taxable income | If this fails, the free estimate scares users with a WRONG number and destroys the trust the product sells. Matches differentiator #4 in §2 | ⬜ |
| R14 | What can be confidently inferred from exógena (mandatory contributions, withholdings) and what cannot | Defines the boundary of the free estimate; when ambiguous, show a range, not a single figure | ⬜ |

---

## 8. 🟦 Backlog — User Stories

> Format: **As a** [role] **I want** [action] **so that** [benefit]. Each story includes verifiable **Acceptance Criteria (AC)**.

### Epic P0 — Calculation Engine (Excel)

**US-P0-000 · Parameter & rule verification fichas** *(new in v0.3)* `[R]`
*As* the product owner, *I want* every parameter value and calculation rule (R1–R10) verified against its primary source with a written ficha, *so that* the engine's logic is personally owned and defensible.
- AC1: One ficha per rule: rule in own words + numeric example + expected test result.
- AC2: US-P0-001's AC values are confirmed or corrected against the fichas.
- AC3: Every ficha's example exists as a case in the validation suite (US-P0-008).

**US-P0-001 · Parameters Sheet** `[R+A]`
*As* the engine builder, *I want* a sheet with the UVT and all thresholds/limits by tax year, *so that* the engine doesn't depend on hardcoded values and works every year.
- AC1: A `Parameters` sheet exists with a "Tax year" column.
- AC2: Includes UVT 2025 = 49,799 COP and thresholds: income/purchases/deposits (1,400 UVT), net worth (4,500 UVT). *(pending ficha — see US-P0-000)*
- AC3: Includes limits: general 40% cap; 25% exempt income (cap 790 UVT); dependents (384 UVT or 10%); mortgage interest (100 UVT); prepaid health (192 UVT); GMF (50%); additional dependents (72 UVT each, max 4). *(pending ficha)*
- AC4: All other sheets reference this one via formula (zero hardcoded literals).

**US-P0-002 · Input Capture** `[A]`
*As* a user, *I want* an input sheet with clearly marked cells, *so that* I know exactly what to enter.
- AC1: Input cells visually distinguishable (e.g., blue fill).
- AC2: Grouped by: income, mandatory contributions (INCRNGO), deductions, exempt income, net worth.
- AC3: Each cell has a clear label and unit.

**US-P0-003 · Filing Obligation Determination (4 thresholds)** `[R+A]`
*As* a user, *I want* to know whether I'm required to file based on the 4 thresholds, *so that* I can decide whether to continue.
- AC1: Evaluates income, purchases, bank deposits, and net worth against their thresholds.
- AC2: Indicates which threshold(s) were exceeded.
- AC3: Clear result: "Required / Not required" + reason.

**US-P0-004 · General Schedule Calculation (Labor Income)** `[R+A]`
*As* an employee, *I want* my taxable income calculated by applying non-taxable income, deductions, and exempt income under the 40% cap, *so that* I get the taxable base.
- AC1–AC2: *(as v0.2)* applies INCRNGO, deductions and exemptions in the legally correct order.
- AC3: Applies the general 40% cap on deductions + exempt income combined.
- AC4: Separates **capped** deductions from **uncapped** ones (additional dependents 72 UVT; 1% electronic-invoiced purchases).
- AC5: Reproduces the result of Example #1 from the reference webinar.

**US-P0-005 · Tax Liability Calculation** `[R+A]`
*As* a user, *I want* the tax calculated using the Art. 241 progressive table, *so that* I know the amount owed.
- AC1: Converts the taxable base to UVT using that year's UVT value.
- AC2: Locates the correct bracket and applies the table's formula.
- AC3: For Example #1 (employee), yields **$0** tax due.

**US-P0-006 · Wealth/Equity Comparison** `[R+A]`
*As* a user, *I want* to compare last year's net worth vs. this year's, *so that* I can spot unjustified increases.
- AC1: Calculates the change in net worth.
- AC2: Flags whether the increase is unsupported by income/appreciation/occasional gains.

**US-P0-007 · Output Mapped to Form 210** `[R+A]`
*As* a user, *I want* to see which value goes in each line of Form 210, *so that* I can transfer it to the DIAN.
- AC1: Line → value table.
- AC2: Covers net worth (29–31), labor income (32–42 / 43–57), occasional gains, tax due, advance payment, balance.
- AC3: Every line traceable back to an Input or a Parameter.

**US-P0-008 · Validation Test Suite** `[R]`
*As* the product owner, *I want* a set of test cases with expected results, *so that* I can confirm the engine is reliable.
- AC1: Includes my **3** real filed tax returns.
- AC2: Includes the webinar's worked examples (employee → $0; refund 1,886,000 COP; self-employed ×3; capital income; non-labor; pensions; occasional gains).
- AC3: Pass criterion: $0 difference on key lines.

**US-P0-009 · Willingness-to-pay probe** *(new in v0.3)* `[R]`
*As* the product owner, *I want* to show the validated Excel result to ≥3 people in the target profile and record whether/what they'd pay for tier X, *so that* P2 investment is gated on demand signal, not assumption (M5).
- AC1: ≥3 recorded responses: would pay yes/no + amount + what convinced/blocked them.
- AC2: Result logged in the Decision Log with a go/no-go/adjust note for P2.

### Later Epics (epic-level, to be broken down when reached)

- **US-P1-001** — Accountant review protocol for raising observations on the engine.
- **US-P1-002** — Self-employed: compare the 3 scenarios (real costs / 25% exempt / presumptive).
- **US-P2-001** — Login tied to userId; resume later.
- **US-P2-002** — Menu routing: DIAN validator or simulator.
- **US-P2-003** — Clear disclaimer (draft, not advice).
- **US-P2-004** *(moved from P3, now MVP-product critical path)* — Upload exógena file (with curated YouTube download guide) → parse, depurate (R11–R14), extract 4 thresholds and breakdown → free-tier conservative estimate + deadline + applicable rules.
- **US-P2-005** *(new)* — Tier X purchase flow: full liquidation + deduction validation + normative breakdown + documents checklist.
- **US-P3-001** — Upload certificates one at a time with fail-fast validation (OCR).
- **US-P4-001** — Field-level tooltips and AI alerts; tier Y recommendations *(gated on legal-boundary research)*.

---

## 9. 🟩 [TEMPLATE] Risk Register

> Scale: Prob./Impact = Low / Medium / High. **Trigger** = the signal that warns the risk is materializing.

| ID | Risk | Prob. | Impact | Mitigation | Trigger |
|----|------|-------|--------|------------|---------|
| R-1 | **Scope creep** | High | High | Cut by profile (employee first); prioritized backlog; explicit out-of-scope; MVP closing rule (M1+M2) | Tasks appear not tied to M1/M2 |
| R-2 | **Incorrect tax calculation** | Medium | High | Validation suite vs real returns; fichas (US-P0-000); professional review (P1) | A test case shows non-$0 difference |
| R-3 | **Regulatory changes** | High | Medium | Everything parameterized by year; isolated Parameters sheet | New reform / decree |
| R-4 | **Delegating the calculation to AI** | Medium | High | Hard rule: deterministic engine; AI only extracts/explains | Someone proposes "let the LLM calculate it" |
| R-5 | **Exógena depuration fails (false income)** *(elevated in v0.3, was OCR-focused)* | High | High | R11–R14 fichas calibrated against real exógena↔declaration pairs; conservative framing; show ranges when ambiguous; manual correction always available | Free estimate deviates grossly from a known real return |
| R-6 | **Third-party personal data (Law 1581)** | Medium | High | Consent/security designed from P2; educational positioning | First user other than myself |
| R-7 | **TY2025 exógena not yet available (~July)** | High | Low | Work P0 with 2024 data / own past returns | Calendar |
| R-8 | **Over-engineering the methodology** | Medium | Medium | Lightweight Kanban; weekly review; no heavy ceremonies | I stop updating the board |
| R-9 | **Legal boundary: "estimation tool" vs "tax advisory"** *(new)* | Medium | High | Research before charging (blocks tier Y; informs X disclaimer) | Drafting the X paywall copy |
| R-10 | **Extreme seasonality** *(new)* | High | Medium | Aug–Oct peak dates the go-to-market backwards from R10 (calendar rule); off-season demand ≈ 0 | Product ready after the season's peak |

---

## 10. 🟩 [TEMPLATE] Working Methodology

**Board tool: GitHub (Issues + Projects).**
- Each User Story = one **issue** (labeled by phase and epic).
- **GitHub Projects** Kanban board.
- Backlog and this plan live as **version-controlled markdown in the repo** → single source of truth, agent-accessible.

**Owner tags** *(new in v0.3 — operationalizes the vibe-coding boundary)*: every story/task carries one:
- `[R]` — Domain. Executed personally by Rumpel (calculation rules, normative validation, business decisions). Agents may assist research, never decide.
- `[A]` — Scaffolding. Delegable to agents (UI, boilerplate, structure, deploy).
- `[R+A]` — Collaborative. Agent proposes, Rumpel validates and owns the result.

**Board (Kanban):** `Backlog` → `Ready` → `In Progress` → `In Review` → `Done`.
**Cadence:** weekly review (15–30 min).
**WIP limit:** max 1–2 stories `In Progress`.
**Estimation:** S / M / L. No story points at the start.

**Definition of Ready (DoR)** — a story enters `Ready` only if:
- [ ] Clear role, action, benefit.
- [ ] Verifiable acceptance criteria.
- [ ] No dependency on an unfinished story (or dependency explicit).
- [ ] Small enough to close within days (**INVEST**).
- [ ] It has an owner tag. *(new)*

**Definition of Done (DoD)** — a story moves to `Done` only if:
- [ ] All ACs met.
- [ ] Covered by ≥1 validation-suite case (where applicable).
- [ ] Documented (what, where, how to test).
- [ ] Doesn't break previously passing cases.
- [ ] For `[R]` domain stories: fichas written (own words + example + test). *(new)*

---

## 11. 🟦 Recommended Kick-off Sequence (Phase 0)

1. `US-P0-000` Verification fichas for R1, R2, R8, R10 first (they gate everything). *(new first step)*
2. `US-P0-001` Parameters (values confirmed by fichas).
3. `US-P0-002` Input.
4. `US-P0-004` General schedule calculation (+ fichas R3–R7 as reached).
5. `US-P0-005` Tax liability calculation.
6. `US-P0-008` Validation test suite (in parallel, started early).
7. `US-P0-003` Thresholds · `US-P0-006` Wealth comparison · `US-P0-007` Output.
8. `US-P0-009` WTP probe (closes P0's business question).

*Cheap parallel task (no blocker): start collecting real exógena↔declaration pairs beyond your own
(family, friends; "experiment, no commitment" framing) — they feed R11–R14 in P2 and cost ~0 now.*

---

## 12. 🟦 Quick Glossary

- **UVT** (Unidad de Valor Tributario): Colombia's Tax Value Unit (2025: 49,799 COP — pending ficha). Converts thresholds into pesos.
- **INCRNGO**: Non-taxable income (Ingreso No Constitutivo de Renta ni Ganancia Ocasional).
- **Cédula general**: groups labor, capital, and non-labor income.
- **Exógena**: third-party information reported to the DIAN; includes the 4-threshold summary. Since v0.3: the product's entry point.
- **Declaración sugerida**: DIAN's pre-filled form based on exógena; taxpayer remains responsible.
- **Form 210**: income tax return for resident individual taxpayers.
- **Comparación patrimonial**: DIAN's control over year-over-year net worth growth.

---

## 13. Decision Log

| Date | Decision | Status |
|------|----------|--------|
| — | Validation suite = **3** personal past tax returns + webinar examples | ✅ Closed |
| — | MVP profile = **employee (asalariado)** | ✅ Closed |
| — | Board tool = **GitHub (Issues + Projects)** + backlog as markdown in repo | ✅ Closed |
| — | North Star = **"Understand and verify before filing"** (§2) | ✅ Closed |
| 2026-07-06 | Monetization funnel: free ceiling estimate → X (full liquidation) → Y (future-year recommendations) | ✅ Closed |
| 2026-07-06 | Product entry point = **exógena file upload** (nobody types data with discipline); manual entry = fallback + P0 mechanism | ✅ Closed |
| 2026-07-06 | **Exógena parsing moved P3 → P2** — it's on the free tier's critical path; accepts advancing R11–R14 technical risk | ✅ Closed |
| 2026-07-06 | Form 210 draft output stays post-P2 ("coming soon" in X); tier Y gated on legal-boundary research (R-9) | ✅ Closed |
| 2026-07-06 | Parameter values in ACs (webinar-sourced) require verification fichas before being trusted (US-P0-000) | ✅ Closed |
| 2026-07-06 | Owner tags `[R]/[A]/[R+A]` adopted in methodology (vibe-coding boundary, reusable 🟩) | ✅ Closed |
| 2026-07-06 | No committed deadline; Tabris and P0 advance in parallel ASAP; entering mid-season 2026 acceptable for early validation | ✅ Closed |

**Open items / to refine**
- [ ] Define the exact "key lines" for the $0-difference criterion (M1).
- [ ] Define a measurable North Star Metric for when real users exist (proposal in §2).
- [ ] Confirm the Art. 241 table and the fiscal adjustment (reajuste fiscal) in Parameters *(→ US-P0-000, ficha R8)*.
- [ ] Price hypothesis for X: anchor range in COP before US-P0-009 so the probe asks about a concrete number.