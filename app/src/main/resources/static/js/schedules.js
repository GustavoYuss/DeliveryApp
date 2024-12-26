function addEntry() {
    const container = document.querySelector('.holiday-schedule');
    const template = `
    <div class="holiday-schedule-entry">
      <label for="holiday-date" class="holiday-schedule-label">Fecha</label>
      <input type="date" class="holiday-schedule-date" />

      <label class="holiday-schedule-closed">
        <input type="checkbox" class="holiday-schedule-checkbox" />
        Cerrado todo el día
      </label>

      <label for="holiday-open-time" class="holiday-schedule-label">Horario de apertura</label>
      <input type="time" class="holiday-schedule-time" />

      <label for="holiday-close-time" class="holiday-schedule-label">Hora de cierre</label>
      <input type="time" class="holiday-schedule-time" />

      <button class="holiday-schedule-remove" onclick="removeEntry(this)">X</button>
    </div>
  `;
    container.insertAdjacentHTML('beforeend', template);
}

function removeEntry(button) {
    const entry = button.parentElement;
    entry.remove();
}
