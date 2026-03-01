import { useMemo, useState } from "react";

const DEFAULT_VISIBLE_CODE = `def twoSum(nums, target):
    seen = {}
    for i, num in enumerate(nums):
        complement = target - num
        if complement in seen:
            return [seen[complement], i]
        seen[num] = i
    return []`;

const PYTHON_HIDDEN_FOOTER = `import json
input_data = json.loads('{{INPUT}}')
nums = input_data['nums']
target = input_data['target']
result = twoSum(nums, target)
print(json.dumps(result))`;

function parseKeyValueInput(rawInput) {
  try {
    const parsed = JSON.parse(rawInput);
    if (parsed && typeof parsed === "object" && !Array.isArray(parsed)) {
      return { data: parsed, error: "" };
    }
    return { data: null, error: "Input JSON must be an object for key-value preview." };
  } catch (error) {
    return { data: null, error: "Invalid JSON input." };
  }
}

function App() {
  const [language, setLanguage] = useState("python");
  const [code, setCode] = useState(DEFAULT_VISIBLE_CODE);
  const [timeoutSeconds, setTimeoutSeconds] = useState(5);
  const [testCases, setTestCases] = useState([
    { input: '{"nums": [2, 7, 11, 15], "target": 9}', expectedOutput: "[0, 1]" },
    { input: '{"nums": [3, 2, 4], "target": 6}', expectedOutput: "[1, 2]" }
  ]);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const finalCode = useMemo(() => {
    if (language !== "python") {
      return code;
    }
    return `${code.trimEnd()}\n\n${PYTHON_HIDDEN_FOOTER}`;
  }, [language, code]);

  const payload = useMemo(
    () => ({
      language,
      code: finalCode,
      testCases,
      timeoutSeconds: Number(timeoutSeconds) || 5
    }),
    [language, finalCode, testCases, timeoutSeconds]
  );

  const updateTestCase = (index, key, value) => {
    setTestCases((current) =>
      current.map((item, i) => (i === index ? { ...item, [key]: value } : item))
    );
  };

  const addTestCase = () => {
    setTestCases((current) => [...current, { input: "", expectedOutput: "" }]);
  };

  const removeTestCase = (index) => {
    setTestCases((current) => current.filter((_, i) => i !== index));
  };

  const runCode = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError("");
    setResult(null);

    try {
      const response = await fetch("/api/v1/execute", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      });

      const data = await response.json();
      if (!response.ok) {
        throw new Error(data?.message || "Execution failed");
      }
      setResult(data);
    } catch (err) {
      setError(err.message || "Unable to execute code");
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="min-h-screen bg-[radial-gradient(circle_at_top,_#1e293b_0%,_#020617_50%,_#000_100%)] px-4 py-8 text-slate-100">
      <div className="mx-auto grid w-full max-w-7xl gap-6 lg:grid-cols-[1.2fr_1fr]">
        <section className="rounded-2xl border border-slate-700/60 bg-slate-900/75 p-6 shadow-2xl backdrop-blur">
          <div className="mb-6">
            <h1 className="text-3xl font-bold tracking-tight text-cyan-300">Code Executor</h1>
            <p className="mt-2 text-sm text-slate-300">
              Tailwind-powered UI with automatic hidden Python input wrapper.
            </p>
            <div className="mt-3 inline-flex rounded-full border border-cyan-400/30 bg-cyan-400/10 px-3 py-1 text-xs text-cyan-200">
              Hidden input parser is auto-injected for Python requests
            </div>
          </div>

          <form onSubmit={runCode} className="space-y-5">
            <div className="grid gap-4 sm:grid-cols-2">
              <label className="block text-sm font-medium text-slate-200">
                Language
                <select
                  value={language}
                  onChange={(e) => setLanguage(e.target.value)}
                  className="mt-2 w-full rounded-lg border border-slate-700 bg-slate-950 px-3 py-2 text-slate-100 outline-none ring-cyan-400 transition focus:ring-2"
                >
                  <option value="python">python</option>
                  <option value="java">java</option>
                  <option value="cpp">cpp</option>
                  <option value="javascript">javascript</option>
                  <option value="go">go</option>
                </select>
              </label>

              <label className="block text-sm font-medium text-slate-200">
                Timeout (seconds)
                <input
                  type="number"
                  min="1"
                  value={timeoutSeconds}
                  onChange={(e) => setTimeoutSeconds(e.target.value)}
                  className="mt-2 w-full rounded-lg border border-slate-700 bg-slate-950 px-3 py-2 text-slate-100 outline-none ring-cyan-400 transition focus:ring-2"
                />
              </label>
            </div>

            <label className="block text-sm font-medium text-slate-200">
              Code
              <textarea
                rows="14"
                value={code}
                onChange={(e) => setCode(e.target.value)}
                placeholder="Write your function here"
                className="mt-2 w-full rounded-xl border border-slate-700 bg-slate-950 px-3 py-3 font-mono text-sm text-slate-100 outline-none ring-cyan-400 transition focus:ring-2"
              />
            </label>

            <div className="flex items-center justify-between">
              <h2 className="text-lg font-semibold text-slate-100">Test Cases</h2>
              <button
                type="button"
                onClick={addTestCase}
                className="rounded-lg bg-cyan-600 px-3 py-2 text-sm font-semibold text-white transition hover:bg-cyan-500"
              >
                Add Case
              </button>
            </div>

            <div className="space-y-3">
              {testCases.map((testCase, index) => (
                <div
                  key={index}
                  className="rounded-xl border border-slate-700 bg-slate-950/80 p-4"
                >
                  {(() => {
                    const parsedPreview = parseKeyValueInput(testCase.input);
                    return (
                      <>
                  <div className="mb-3 flex items-center justify-between">
                    <strong className="text-cyan-200">Case {index + 1}</strong>
                    <button
                      type="button"
                      onClick={() => removeTestCase(index)}
                      disabled={testCases.length === 1}
                      className="rounded-md bg-slate-800 px-3 py-1.5 text-xs text-slate-200 transition hover:bg-slate-700 disabled:cursor-not-allowed disabled:opacity-50"
                    >
                      Remove
                    </button>
                  </div>

                  <label className="mb-3 block text-sm text-slate-300">
                    Input
                    <textarea
                      rows="3"
                      value={testCase.input}
                      onChange={(e) => updateTestCase(index, "input", e.target.value)}
                      className="mt-1 w-full rounded-lg border border-slate-700 bg-slate-900 px-3 py-2 font-mono text-xs text-slate-100 outline-none ring-cyan-400 transition focus:ring-2"
                    />
                  </label>

                  <div className="mb-3 rounded-lg border border-slate-700 bg-slate-900/70 p-3">
                    <p className="mb-2 text-xs font-semibold uppercase tracking-wide text-cyan-200">
                      Parsed Input (Key-Value)
                    </p>
                    {parsedPreview.data ? (
                      <div className="space-y-1 text-xs">
                        {Object.entries(parsedPreview.data).map(([key, value]) => (
                          <p key={key} className="text-slate-200">
                            <span className="font-semibold text-cyan-300">{key}:</span>{" "}
                            <span className="font-mono text-slate-100">{JSON.stringify(value)}</span>
                          </p>
                        ))}
                      </div>
                    ) : (
                      <p className="text-xs text-amber-300">{parsedPreview.error}</p>
                    )}
                  </div>

                  <label className="block text-sm text-slate-300">
                    Expected Output
                    <input
                      value={testCase.expectedOutput}
                      onChange={(e) => updateTestCase(index, "expectedOutput", e.target.value)}
                      className="mt-1 w-full rounded-lg border border-slate-700 bg-slate-900 px-3 py-2 font-mono text-xs text-slate-100 outline-none ring-cyan-400 transition focus:ring-2"
                    />
                  </label>
                </>);
                  })()}
                </div>
              ))}
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full rounded-xl bg-gradient-to-r from-blue-600 to-cyan-500 px-4 py-3 font-semibold text-white transition hover:from-blue-500 hover:to-cyan-400 disabled:cursor-not-allowed disabled:opacity-60"
            >
              {loading ? "Running..." : "Run Code"}
            </button>
          </form>
        </section>

        <section className="rounded-2xl border border-slate-700/60 bg-slate-900/75 p-6 shadow-2xl backdrop-blur">
          <h2 className="text-2xl font-semibold text-slate-100">Execution Result</h2>

          {error && (
            <div className="mt-4 rounded-xl border border-red-500/30 bg-red-500/10 px-4 py-3 text-red-300">
              {error}
            </div>
          )}

          {!error && !result && (
            <p className="mt-4 rounded-xl border border-slate-700 bg-slate-950/70 px-4 py-3 text-slate-300">
              No execution yet.
            </p>
          )}

          {result && (
            <div className="mt-4 space-y-4">
              <div className="rounded-xl border border-slate-700 bg-slate-950/80 p-4 text-sm text-slate-200">
                <p>
                  <span className="font-semibold text-cyan-200">Message:</span> {result.message}
                </p>
                <p>
                  <span className="font-semibold text-cyan-200">Success:</span> {String(result.success)}
                </p>
                <p>
                  <span className="font-semibold text-cyan-200">Total Time:</span> {result.executionTimeMs} ms
                </p>
                {result.error && (
                  <p className="mt-2 text-red-300">
                    <span className="font-semibold">Error:</span> {result.error}
                  </p>
                )}
              </div>

              <div className="space-y-3">
                {(result.testCaseResult || []).map((item, index) => (
                  <article
                    key={index}
                    className={`rounded-xl border p-4 text-sm ${
                      item.passed
                        ? "border-emerald-500/50 bg-emerald-500/10 text-emerald-200"
                        : "border-red-500/50 bg-red-500/10 text-red-200"
                    }`}
                  >
                    <h3 className="mb-2 text-base font-semibold">
                      Case {index + 1}: {item.passed ? "Passed" : "Failed"}
                    </h3>
                    <p><span className="font-semibold">Input:</span> {item.input}</p>
                    <p><span className="font-semibold">Expected:</span> {item.expectedOutputResult}</p>
                    <p><span className="font-semibold">Actual:</span> {item.actualOutput}</p>
                    {item.error && <p><span className="font-semibold">Error:</span> {item.error}</p>}
                    <p><span className="font-semibold">Execution Time:</span> {item.executionTimeMs} ms</p>
                  </article>
                ))}
              </div>
            </div>
          )}
        </section>
      </div>
    </main>
  );
}

export default App;
