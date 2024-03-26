package cn.todo.utils.aop;

public interface AuditLoggable<T> {
    public void auditLog(T t, String eventName);
}
