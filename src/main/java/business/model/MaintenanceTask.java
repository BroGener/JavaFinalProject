package business.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a maintenance task assigned to a maintainer for a specific scooter.
 * <p>
 * A {@code MaintenanceTask} captures the details of a scheduled or ongoing
 * maintenance job, including which scooter needs servicing, which maintainer
 * is responsible, a description of the work required, its priority level,
 * current status, and the due date for completion. This class implements
 * {@link Serializable} to support persistence and data transfer.
 * </p>
 */
public class MaintenanceTask implements Serializable {

    /** The unique identifier for this maintenance task. */
    private Integer taskId;

    /** The ID of the scooter this task is assigned to. */
    private Integer scooterId;

    /** The ID of the maintainer user responsible for completing this task. */
    private Integer maintainerUserId;

    /** A description of the maintenance work to be performed. */
    private String description;

    /** The priority level of this task (e.g., {@code "LOW"}, {@code "MEDIUM"}, {@code "HIGH"}). */
    private String priority;

    /** The current status of this task (e.g., {@code "PENDING"}, {@code "IN_PROGRESS"}, {@code "COMPLETED"}). */
    private String status;

    /** The date by which this maintenance task should be completed. */
    private LocalDate dueDate;

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks that instantiate objects reflectively
     * (e.g., ORM or serialization libraries).
     * </p>
     */
    public MaintenanceTask() {}

    /**
     * Constructs a fully initialized {@code MaintenanceTask} with all fields specified.
     *
     * @param taskId            the unique identifier for this task, or {@code null} if not yet persisted
     * @param scooterId         the ID of the scooter requiring maintenance
     * @param maintainerUserId  the ID of the maintainer assigned to this task
     * @param description       a description of the maintenance work to be performed
     * @param priority          the priority level (e.g., {@code "LOW"}, {@code "MEDIUM"}, {@code "HIGH"})
     * @param status            the current status (e.g., {@code "PENDING"}, {@code "IN_PROGRESS"}, {@code "COMPLETED"})
     * @param dueDate           the date by which this task should be completed
     */
    public MaintenanceTask(Integer taskId, Integer scooterId, Integer maintainerUserId,
                           String description, String priority, String status, LocalDate dueDate) {
        this.taskId = taskId;
        this.scooterId = scooterId;
        this.maintainerUserId = maintainerUserId;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
    }

    /**
     * Returns the unique identifier of this maintenance task.
     *
     * @return the task ID, or {@code null} if not yet persisted
     */
    public Integer getTaskId() { return taskId; }

    /**
     * Sets the unique identifier of this maintenance task.
     *
     * @param taskId the task ID to assign
     */
    public void setTaskId(Integer taskId) { this.taskId = taskId; }

    /**
     * Returns the ID of the scooter this task is assigned to.
     *
     * @return the scooter ID
     */
    public Integer getScooterId() { return scooterId; }

    /**
     * Sets the ID of the scooter this task is assigned to.
     *
     * @param scooterId the scooter ID to assign
     */
    public void setScooterId(Integer scooterId) { this.scooterId = scooterId; }

    /**
     * Returns the ID of the maintainer user responsible for this task.
     *
     * @return the maintainer user ID
     */
    public Integer getMaintainerUserId() { return maintainerUserId; }

    /**
     * Sets the ID of the maintainer user responsible for this task.
     *
     * @param maintainerUserId the maintainer user ID to assign
     */
    public void setMaintainerUserId(Integer maintainerUserId) { this.maintainerUserId = maintainerUserId; }

    /**
     * Returns a description of the maintenance work to be performed.
     *
     * @return the task description
     */
    public String getDescription() { return description; }

    /**
     * Sets a description of the maintenance work to be performed.
     *
     * @param description the task description to assign
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Returns the priority level of this maintenance task.
     *
     * @return the priority (e.g., {@code "LOW"}, {@code "MEDIUM"}, {@code "HIGH"})
     */
    public String getPriority() { return priority; }

    /**
     * Sets the priority level of this maintenance task.
     *
     * @param priority the priority to assign (e.g., {@code "LOW"}, {@code "MEDIUM"}, {@code "HIGH"})
     */
    public void setPriority(String priority) { this.priority = priority; }

    /**
     * Returns the current status of this maintenance task.
     *
     * @return the status (e.g., {@code "PENDING"}, {@code "IN_PROGRESS"}, {@code "COMPLETED"})
     */
    public String getStatus() { return status; }

    /**
     * Sets the current status of this maintenance task.
     *
     * @param status the status to assign (e.g., {@code "PENDING"}, {@code "IN_PROGRESS"}, {@code "COMPLETED"})
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Returns the date by which this maintenance task should be completed.
     *
     * @return the due date as a {@link LocalDate}
     */
    public LocalDate getDueDate() { return dueDate; }

    /**
     * Sets the date by which this maintenance task should be completed.
     *
     * @param dueDate the due date to assign
     */
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}